import Vue from "vue";
import Router from "vue-router";
import ContactList from "./components/ContactList.vue";
import AddContact from "./components/AddContact.vue";
import SearchContact from "./components/SearchContact.vue";
import ContactDetail from "./components/Contact.vue";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            name: "contact",
            alias: "/contact",
            component: ContactList,
        },
        {
            path: "/contact/:id",
            name: "ContactDetail",
            component: ContactDetail
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