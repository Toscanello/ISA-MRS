import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePagePharmacist from '../views/HomePagePharmacist.vue'
import HomePageDermatologist from '../views/HomePageDermatologist.vue'
import PharmacyAdminHome from '../views/PharmacyAdminHome.vue'
import Pharmacy from '../views/Pharmacy.vue'
import HomePagePatient from '../views/HomePagePatient.vue'
import * as VueGoogleMaps from 'vue2-google-maps'
import SystemAdminHome from '../views/SystemAdminHome.vue'
import AppointmenPharmacist from '../views/patient/AppointmenPharmacist.vue'
import PharmacyDermatologists from '../views/PharmacyDermatologists.vue'
import DermatologistAppointmentForm from '../views/DermatologistAppointmentForm.vue'
import PharmacistNewAppointment from '../components/PharmacistNewAppointment.vue'
import PharmacyEdit from '../views/PharmacyEdit.vue'
import Login from '../views/Login.vue'
import Calendar from '../views/CalendarPatient.vue'
import MedicineList from '../views/MedicineList.vue'
import MedicinePricing from '../views/MedicinePricing.vue'
import PharmacistCalendar from '../components/PharmacistCalendar.vue'
import DermatologistAppointment from '../views/patient/DermatologistAppointment.vue'
import TokenDecoder from '../services/token-decoder'

Vue.use(VueRouter)
Vue.use(VueGoogleMaps, {
  load: {
    key: ''
  }
})

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    beforeEnter: (to, from, next) => {
        let role = TokenDecoder.getUserRole()
        if (role == 'ROLE_USER') {  /* TODO: ROLE_PATIENT */
          next({name: 'HomePagePatient'})
        }
        else if (role == 'ROLE_PHARMACIST') {
          next({name: 'HomePagePharmacist'})
        }
        else if (role == 'ROLE_DERMATOLOGIST') {
          next({name: 'HomePageDermatologist'})
        }
        else if (role == 'ROLE_SYSADMIN') {
          next({name: 'SystemAdminHome'})
        }
        else 
          next({name: 'Login'})
    }
  },
  {
    path: '/calendar',
    name: 'Calendar',
    component: Calendar
  },
  {
    path: '/pharmacist-calendar',
    name: 'PharmacistCalendar',
    component: PharmacistCalendar
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
    path: '/dermatologistAppointment/:regNo',
    name: 'DermatologistAppointment',
    component: DermatologistAppointment
  },
  {
    path: '/home-patient',
    name: 'HomePagePatient',
    component: HomePagePatient
  },
  {
    path: '/appointment-pharmacist',
    name: 'AppointmenPharmacist',
    component: AppointmenPharmacist
  },
  {
    path: '/systemadmin',
    name: 'SystemAdminHome',
    component: SystemAdminHome
  },
  {
    path: '/pharmacyadmin',
    name: 'PharmacyAdminHome',
    component: PharmacyAdminHome
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
    path: '/pharmacist/:email/:regNo/appointment',
    name: 'PharmacistNewAppointment',
    component: PharmacistNewAppointment
  },
  {
    path: '/edit/pharmacy/:regNo',
    name: 'PharmacyEdit',
    component: PharmacyEdit
  },
  {
    path: '/pricing/pharmacy/:regNo',
    name: 'MedicineList',
    component: MedicineList
  },
  {
    path: '/pricing/medicine/:regNo/:code',
    name: 'MedicinePricing',
    component: MedicinePricing
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
