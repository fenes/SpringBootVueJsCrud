<template>
    <div class="submitform">
        <div v-if="!submitted">
            <h3>Contact Detail / Edit</h3>
            <div class="card">
                <div class="card-header">
                    Contact Detail (Cached by Redis)
                </div>
                <div class="card-body">
                    <div class="contactBody" v-if="contact">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input placeholder="Name" type="text" class="form-control" id="name" required
                                   v-model="contact.name" name="name">
                        </div>
                        <div class="form-group">
                            <label for="surname">Surname</label>
                            <input placeholder="Surname" type="text" class="form-control" id="surname" required
                                   v-model="contact.surname"
                                   name="surname">
                        </div>
                        <div class="form-group">
                            <label for="surname">Work Info</label>
                            <input placeholder="Work Info" type="text" class="form-control" id="workInfo" required
                                   v-model="contact.workInfo"
                                   name="workInfo">
                        </div>
                        <div class="form-group">
                            <label for="surname">Birthday</label>
                            <datepicker required placeholder="Select Date" v-model="contact.birthday"></datepicker>
                        </div>
                        <div class="table-responsive" v-if="this.contact.phoneList.length != 0">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">
                                        Phone Type
                                    </th>
                                    <th>
                                        Phone Number
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="phone in this.contact.phoneList" v-bind:key="phone.id">
                                    <td><input placeholder="Phone Type" v-model="phone.key" type="text"></td>
                                    <td>
                                        <the-mask placeholder="Phone Number" mask="(###) ### ## ##"
                                                  v-model="phone.value"/>
                                    </td>
                                    <td>
                                        <a href="#" class="icon">
                                            <i v-on:click="deletePhoneRow(product)" class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <button class="btn btn-secondary" @click="addPhoneRow"> Add Phone</button>
                        <br><br>
                        <div class="table-responsive" v-if="this.contact.addressList.length != 0">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">
                                        Address Type
                                    </th>
                                    <th>
                                        Address Value
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="address in this.contact.addressList" v-bind:key="address.id">
                                    <td><input placeholder="Address Type" v-model="address.key" type="text"></td>
                                    <td>
                                    <textarea placeholder="Address" v-model="address.value" name="addressValue"
                                              cols="30" rows="3"></textarea>
                                    </td>
                                    <td>
                                        <a href="#" class="icon">
                                            <i v-on:click="deleteAddressRow(address)" class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <button class="btn btn-secondary" @click="addAddressRow"> Add Address</button>
                        <br><br>
                        <router-link :to="'/'" class="btn btn-primary"> Back</router-link>
                        <button v-on:click="updateContact" class="btn btn-success"> Update</button>
                    </div>
                    <div v-else>
                        <h4>This contact id ({{paramId}}) is not exist!</h4>
                    </div>
                </div>
            </div>
        </div>
        <div v-else>
            <h3>You {{addResult}} submitted !</h3>
            <router-link :to="'/'" class="btn btn-success"> Go List Page</router-link>
        </div>
    </div>
</template>
<script>
    import http from "../http-common";

    import Datepicker from 'vuejs-datepicker';


    export default {
        name: "add-contact",
        components: {
            Datepicker
        },
        data() {
            return {
                contact: {
                    id: 0,
                    name: "",
                    surname: "",
                    workInfo: "",
                    birthday: "",
                    phoneList: [],
                    addressList: []
                },
                submitted: false,
                paramId: 0,
                addResult: false
            };
        },
        beforeCreate() {
            http
                .get("/contact/" + this.$route.params.id)
                .then(response => {
                    this.contact = response.data; // JSON are parsed automatically.
                }, (response) => {
                    if(response){
                    this.paramId =  this.$route.params.id;
                    this.contact = null;
                    }
                })
        },
        methods: {
            addPhoneRow() {
                this.contact.phoneList.push({
                    key: '',
                    value: ''
                })
            },
            deletePhoneRow(phone) {
                this.contact.phoneList.splice(this.contact.phoneList.indexOf(phone), 1)
            },
            addAddressRow() {
                this.contact.addressList.push({
                    key: '',
                    value: ''
                })
            },
            deleteAddressRow(address) {
                this.contact.addressList.splice(this.contact.addressList.indexOf(address), 1)
            },
            /* eslint-disable no-console */
            updateContact() {
                var data = this.contact;

                http
                    .put("/contact", data)
                    .then(response => {
                        this.submitted = true;
                        this.addResult = response.data ? "successfully" : "cant";
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            newcontact() {
                this.submitted = false;
                this.contact = {};
            }
        }
    };
</script>

<style scoped>
    h3 {
        text-align: center;
        margin-top: 30px;
        margin-bottom: 20px;
    }
</style>