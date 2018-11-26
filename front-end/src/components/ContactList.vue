<template>
    <div class="Contacts">
        <div class="card mt-5">
            <div class="card-header">
                Contact List
            </div>
            <div class="card-body">
                <div class="table" v-if="contact.length > 0">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">
                                Name
                            </th>
                            <th>
                                Surname
                            </th>
                            <th>
                                Work Info
                            </th>
                            <th>
                                Birthday
                            </th>
                            <th>
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="contact in sortedContacts" v-bind:key="contact.id">
                            <template v-if="editId == contact.id">
                                <td><input v-model="editContactData.name" type="text"></td>
                                <td><input v-model="editContactData.surname" type="text"></td>
                                <td><input v-model="editContactData.workInfo" type="text"></td>
                                <td>
                                    <datepicker v-model="editContactData.birthday"></datepicker>
                                </td>
                                <td>
                    <span class="icon">
                      <i @click="updateContact()" class="fa fa-check"></i>
                    </span>
                                    <span class="icon">
                      <i @click="onCancel" class="fa fa-ban"></i>
                    </span>
                                </td>
                            </template>
                            <template v-else>
                                <td>
                                    {{contact.name}}
                                </td>
                                <td>
                                    {{contact.surname}}
                                </td>
                                <td>
                                    {{contact.workInfo}}
                                </td>
                                <td>
                                    {{GenerateStringFromDate(contact.birthday)}}
                                </td>
                                <td>
                                    <router-link title="Show Detail"
                                                 :to="{name: 'ContactDetail', params:{id: contact.id}  }"
                                                 class="icon">
                                        <i class="fa fa-eye"></i>
                                    </router-link>
                                    <a href="#" class="icon" title="Edit">
                                        <i v-on:click="onEdit(contact)" class="fa fa-pencil"></i>
                                    </a>

                                    <a href="#" class="icon" title="Delete!">
                                        <i v-on:click="deleteContact(contact.id)" class="fa fa-trash"></i>
                                    </a>
                                </td>
                            </template>
                        </tr>

                        </tbody>
                    </table>
                </div>
                <div v-else>
                    <h4>List is empty!</h4>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    import http from "../http-common";
    import moment from 'moment'
    import Datepicker from 'vuejs-datepicker';

    export default {
        name: "contact-list",
        components: {
            Datepicker
        },
        data() {
            return {
                editId: '',
                editContactData: {
                    'id': '',
                    'name': '',
                    'surname': '',
                    'workInfo': '',
                    'birthday': '',
                    'phoneList': [],
                    'addressList': []
                },
                contact: []
            };
        },
        computed: {
            sortedContacts() {
                return this.contact.slice().sort((a, b) => {
                    return a.id - b.id
                })
            }
        },
        methods: {
            /**
             * @return {string}
             */
            GenerateStringFromDate: function (value) {
                if (value) {
                    return moment(String(value)).format('DD/MM/YYYY')
                }
            },
            /* eslint-disable no-console */
            retrievecontact() {
                http
                    .get("/contact")
                    .then(response => {
                        this.contact = response.data; // JSON are parsed automatically.
                    }, (response) => {
                        if(response){
                            this.contact=[];
                            }
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            refreshList() {
                this.retrievecontact();
            },

            onEdit(contact) {
                this.editId = contact.id;
                this.editContactData.id = contact.id;
                this.editContactData.name = contact.name;
                this.editContactData.surname = contact.surname;
                this.editContactData.workInfo = contact.workInfo;
                this.editContactData.birthday = contact.birthday;
                this.editContactData.phoneList = contact.phoneList;
                this.editContactData.addressList = contact.addressList;
            },

            onCancel() {
                this.clearEditContactData();
            },
            clearEditContactData() {
                this.editId = '';
                this.editContactData.id = '';
                this.editContactData.name = '';
                this.editContactData.surname = '';
                this.editContactData.workInfo = '';
                this.editContactData.birthday = '';
                this.editContactData.phoneList = '';
                this.editContactData.addressList = '';
            },
            updateContact() {
                var data = this.editContactData;
                http
                    .put("/contact", data)
                    .then(response => {
                        if (response) {
                            this.clearEditContactData();
                            this.retrievecontact();
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            deleteContact(id) {
                http
                    .delete("/contact/" + id)
                    .then(response => {
                        if (response)
                            this.retrievecontact();
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }

        },
        mounted() {
            this.retrievecontact();
        }
    };
</script>

<style scoped>
    h3 {
        text-align: center;
        margin-top: 30px;
        margin-bottom: 20px;
    }

    .icon {
        margin-right: 10px;
    }

    .icon i {
        cursor: pointer;
    }
</style>
