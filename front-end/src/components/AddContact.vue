<template>
  <div class="submitform">
    <div v-if="!submitted">
        <div class="form-group">
          <label for="name">Name</label>
          <input type="text" class="form-control" id="name" required v-model="contact.name" name="name">
        </div>
    
        <div class="form-group">
          <label for="surname">Surname</label>
          <input type="text" class="form-control" id="surname" required v-model="contact.surName" name="surname">
        </div>
        <div class="form-group">
          <label for="surname">Work Info</label>
          <input type="text" class="form-control" id="workInfo" required v-model="contact.workInfo" name="workInfo">
        </div>
        <div class="form-group">
          <label for="surname">Birthday</label>
        <datepicker required placeholder="Select Date" v-model="contact.birthday"></datepicker>
        </div>
    <ul>
      <li v-for="(input, index) in this.contact.phoneList">
        <input type="text" v-model="input.key"> - {{ input.key }}  
        <input type="text" v-model="input.value"> - {{ input.value }}
        <button @click="deleteRow(index)">Delete</button>
      </li>
      
    <button @click="addRow">Add row</button>
    </ul>
        <button v-on:click="savecontact" class="btn btn-success">Submit</button>
    </div>
    
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" v-on:click="newcontact">Add</button>
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
        birthday:"",
        phoneList:[]
      },
      submitted : false
    };
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
    savecontact() {
   
      http
        .post("/contact", this.contact)
        .then(response => {
          this.contact.id = response.data.id;
          console.log(response.data);
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
    /* eslint-enable no-console */
  }
};
</script>

<style>
.submitform {
  max-width: 300px;
  margin: auto;
}
</style>
