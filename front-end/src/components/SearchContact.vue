<template>
    <div class="searchform">
        <h3>Search</h3>
        <div class="card">
            <div class="card-header">
                Search
            </div>
            <div class="card-body">
                <div class="form-group">
                    <input placeholder="Search! (Name, Surname, Address Type, Address)"
                           type="text" class="form-control" id="searchParam" required v-model="searchParam"
                           name="searchParam">
                </div>

                <div class="btn-group">
                    <button v-on:click="searchcontact" class="btn btn-success">Search</button>
                </div>
                <br><br>
                <div class="Contacts">
                    <div class="card mt-5" v-if="this.contactList.length>0">
                        <div class="card-header">
                            Contact List
                        </div>
                        <div class="card-body">
                            <div class="table">
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
                                            Phone List
                                        </th>
                                        <th>
                                            Address List
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr v-for="contact in contactList" v-bind:key="contact.id">
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
                                            <p v-for="phone in contact.phoneList" v-bind:key="phone.id">
                                                {{phone.key}} - {{phone.value | formatNumber}} <br>
                                            </p>
                                        </td>
                                        <td>
                                            <p v-for="address in contact.addressList" v-bind:key="address.id">
                                                {{address.key}} - {{address.value}} <br>
                                            </p>
                                        </td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div v-else>
                        <h4>{{emptyParam}}</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    import http from "../http-common";
    import moment from 'moment';

    export default {
        name: "search-contact",
        data() {
            return {
                searchParam: '',
                emptyParam: '',
                contactList: []
            };
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
            searchcontact() {
                http
                    .get("/contact/search/" + this.searchParam)
                    .then(response => {
                        this.contactList = response.data;
                        this.emptyParam = this.contactList.length === 0 ? 'Result is empty.' : '';
                    })
                    .catch(e => {
                        console.log(e);
                    });
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