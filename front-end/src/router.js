import Vue from "vue";
import Router from "vue-router";
import contactList from "./components/ContactList.vue";
import AddContact from "./components/AddContact.vue";
import SearchContact from "./components/SearchContact.vue";
import contact from "./components/Contact.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "contact",
      alias: "/contact",
      component: contactList,
      children: [
        {
          path: "/Contact/:id",
          name: "contact-details",
          component: contact,
          props: true
        }
      ]
    },
    {
      path: "/add",
      name: "add",
      component: AddContact
    },
    {
      path: "/search",
      name: "search",
      component: SearchContact
    }
  ]
});