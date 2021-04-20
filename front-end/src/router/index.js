import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import HomePagePharmacist from '../views/HomePagePharmacist.vue'
import HomePageDermatologist from '../views/HomePageDermatologist.vue'
import Pharmacy from '../views/Pharmacy.vue'
import HomePagePatient from '../views/HomePagePatient.vue'
import * as VueGoogleMaps from 'vue2-google-maps'
import SystemAdminHome from '../views/SystemAdminHome.vue'
import PharmaciesList from '../views/patient/PharmaciesList.vue'
import AppointmenPharmacist from '../views/patient/AppointmenPharmacist.vue'
import Pharmacies from '../views/patient/Pharmacies.vue'
import PharmacistList from '../views/patient/PharmacistList.vue'
import PharmacyDermatologists from '../views/PharmacyDermatologists.vue'
import DermatologistAppointmentForm from '../views/DermatologistAppointmentForm.vue'
import PharmacyEdit from '../views/PharmacyEdit.vue'

Vue.use(VueRouter)
Vue.use(VueGoogleMaps, {
  load: {
    key: ''
  }
})

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/home-pharmacist',
    name: 'HomePagePharmacist',
    component: HomePagePharmacist
  },
  {
    path: '/home-dermatologist',
    name: 'HomePageDermatologist',
    component: HomePageDermatologist
  },
  {
    path: '/pharmacy/:regNo',
    name: 'Pharmacy',
    component: Pharmacy
  },
  {
    path: '/home-patient',
    name: 'HomePagePatient',
    component: HomePagePatient
  },
  {
    path: '/pharmacies-list',
    name: 'PharmaciesList',
    component: PharmaciesList
  },
  {
    path: '/appointment-pharmacist',
    name: 'AppointmenPharmacist',
    component: AppointmenPharmacist
  },
  {
    path: '/bla',
    name: 'Pharmacies',
    component: Pharmacies
  },
  {
    path: '/pharmacist-list',
    name: 'PharmacistList',
    component: PharmacistList
  },
  {
    path: '/systemadmin',
    name: 'SystemAdminHome',
    component: SystemAdminHome
  },
  {
    path: '/dermatologists/pharmacy/:regNo',
    name: 'PharmacyDermatologists',
    component: PharmacyDermatologists
  },
  {
    path: '/dermatologists/:email/:regNo/appointment',
    name: 'DermatologistAppointmentForm',
    component: DermatologistAppointmentForm
  },
  {
    path: '/edit/pharmacy/:regNo',
    name: 'PharmacyEdit',
    component: PharmacyEdit
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
