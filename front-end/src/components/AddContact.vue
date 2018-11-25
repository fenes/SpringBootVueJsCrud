<template>
    <div class="submitform">
        <div v-if="!submitted">
            <h3>Add Contact</h3>
            <div class="card">
                <div class="card-header">
                    Contact Detail
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" placeholder="Name" class="form-control" id="name" required
                               v-model="contact.name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="surname">Surname</label>
                        <input type="text" placeholder="Surname" class="form-control" id="surname" required
                               v-model="contact.surname"
                               name="surname">
                    </div>
                    <div class="form-group">
                        <label for="surname">Work Info</label>
                        <input type="text" placeholder="Work Info" class="form-control" id="workInfo" required
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
                                    Phone Value
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="phone in this.contact.phoneList" v-bind:key="phone.id">
                                <td><input placeholder="Phone Type" v-model="phone.key" type="text"></td>
                                <td>
                                    <the-mask placeholder="Write Phone Number" mask="(###) ### ## ##"
                                              v-model="phone.value"/>

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
                    <button v-on:click="savecontact" class="btn btn-success"> Submit</button>
                </div>
            </div>
        </div>
        <div v-else>
            <h3>You submitted successfully!</h3>
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
                submitted: false
            };
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
            savecontact() {

                http
                    .post("/contact", this.contact)
                    .then(response => {
                        this.contact.id = response.data.id;
                    })
                    .catch(e => {
                        console.log(e);
                    });

                this.submitted = true;
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