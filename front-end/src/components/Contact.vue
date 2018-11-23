<template>
  <div v-if="this.contact">
    <h4>contact</h4>
    <div>
      <label>Name: </label> 
            <input type="text" class="form-control" id="name" required v-model="contact.name" name="name">
    </div>
    <div>
      <label>Surname: </label>
            <input type="text" class="form-control" id="surname" required v-model="contact.surName" name="surname">
    </div>
    <div>
      <label>Work Info: </label> 
      <input type="text" class="form-control" id="workInfo" required v-model="contact.workInfo" name="workInfo">
    </div>
  
    <div>
      <label>birthday </label> 
      <datepicker id="birthday" required placeholder="Select Date" v-model="contact.birthday"></datepicker>
    </div>
    <li v-for="(input, index) in this.contact.phoneList">
        <input type="text" v-model="input.key"> - {{ input.key }}  
        <input type="text" v-model="input.value"> - {{ input.value }}
        <button @click="deleteRow(index)">Delete</button>
      </li>
      
    <button @click="addRow">Add row</button>
    <button 
      v-on:click="updateContact()"
      class="button is-small btn-primary">Update</button>
  
    <button class="button is-small btn-danger" v-on:click="deleteContact()">Delete</button>
  </div>
  <div v-else>
    <br/>
    <p>Please click on a contact...</p>
  </div>
</template>

<script>
import http from "../http-common";

import Datepicker from 'vuejs-datepicker';

export default {
  name: "contact",
  props: ["contact"],
  components: {
    Datepicker
  },
  methods: {
    addRow() {
      this.contact.phoneList.push({
        key: '',
        value: ''
      })
    },
    deleteRow(index) {
      this.contact.phoneList.splice(index,1)
    },
    /* eslint-disable no-console */
    updateContact() {
      var data = this.contact;

      http
        .put("/contact", data)
        .then(response => {
          //this.contact.active = response.data.active;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteContact() {
      http
        .delete("/contact/" + this.contact.id)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          this.$router.push('/');
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>
