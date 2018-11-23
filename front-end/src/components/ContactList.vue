<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>contact List</h4>
            <ul>
                <li v-for="(contact, index) in contact" :key="index">
                    <router-link :to="{
                            name: 'contact-details',
                            params: { contact: contact, id: contact.id }
                        }">
                            {{contact.name}}
                    </router-link>
                </li>
            </ul>
        </div>
        <div class="col-md-6">
            <router-view @refreshData="refreshList"></router-view>
        </div>
    </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "contact-list",
  data() {
    return {
      contact: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    retrievecontact() {
      console.log('retrievecontact');

      http
        .get("/contact")
        .then(response => {
          this.contact = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrievecontact();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.retrievecontact();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 850px;
  margin: auto;
}
</style>
