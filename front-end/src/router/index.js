import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePagePharmacist from '../views/HomePagePharmacist.vue'
import HomePageDermatologist from '../views/HomePageDermatologist.vue'
import HomePagePharmacyAdmin from '../views/HomePagePharmacyAdmin.vue'
import Pharmacy from '../views/Pharmacy.vue'
import HomePagePatient from '../views/HomePagePatient.vue'
import * as VueGoogleMaps from 'vue2-google-maps'
import SystemAdminHome from '../views/SystemAdminHome.vue'
import AppointmenPharmacist from '../views/patient/AppointmenPharmacist.vue'
import PharmacyDermatologists from '../views/PharmacyDermatologists.vue'
import DermatologistAppointmentForm from '../views/DermatologistAppointmentForm.vue'
import PharmacistNewAppointment from '../components/PharmacistNewAppointment.vue'
import MedicineOrder from '@/components/patient/MedicineOrder.vue'
import PharmacyEdit from '../views/PharmacyEdit.vue'
import Calendar from '../views/CalendarPatient.vue'
import MedicineList from '../views/MedicineList.vue'
import MedicinePricing from '../views/MedicinePricing.vue'
import PharmacistCalendar from '../components/PharmacistCalendar.vue'
import PharmacyDermatologistList from '../views/dermatologistDisplay/PharmacyDermatologistList.vue'
import AllDermatologistList from '../views/dermatologistDisplay/AllDermatologistList.vue'
import PharmacyPharmacistList from '../views/pharmacistDisplay/PharmacyPharmacistList.vue'
import AllPharmacistList from '../views/pharmacistDisplay/AllPharmacistList.vue'
import PharmacyAdminAccount from '../views/PharmacyAdminAccount.vue'
import VacationRequestTable from '../views/vacationRequests/VacationRequestTable.vue'
import VacationResponseForm from '../views/vacationRequests/VacationResponseForm.vue'
import DermatologistAppointment from '../views/patient/DermatologistAppointment.vue'
import AllMedicineTable from '../views/bulkOrder/AllMedicineTable.vue'
import OrderList from '../views/bulkOrder/OrderList.vue'
import OffersList from '../views/bulkOrder/OffersList.vue'
import MedicineDiscount from '../views/MedicineDiscount.vue'
import UserHomePage from '../views/UserHomePage.vue'
import Login from '../views/Login.vue'
import EmployDermatologist from '../views/addEmployement/EmployDermatologist.vue'
import EmployDermatologistForm from '../views/addEmployement/EmployDermatologistForm.vue'
import EmployPharmacist from '../views/addEmployement/EmployPharmacist.vue'
import EmployPharmacistForm from '../views/addEmployement/EmployPharmacistForm'
import IncomeReport from '../views/reports/incomeReports/IncomeReport.vue'

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
    name: 'UserHomePage',
    component: UserHomePage
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
        else if (role == 'ROLE_PH_ADMIN') {
          next({name: 'HomePagePharmacyAdmin'})
        }
        else 
          next({name: 'UserHomePage'})
    }
  },
  {
    path: '/calendar',
    name: 'Calendar',
    component: Calendar
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/medicine_order',
    name: 'MedicineOrder',
    component: MedicineOrder
  },
  {
    path: '/pharmacist-calendar',
    name: 'PharmacistCalendar',
    component: PharmacistCalendar
  },
  {
    path: '/dermatologistAppointment/:regNo',
    name: 'DermatologistAppointment',
    component: DermatologistAppointment
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
    name: 'HomePagePharmacyAdmin',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'LogUserHomePage'})
      else
        next()
    },
    component: HomePagePharmacyAdmin
  },
  {
    path: '/dermatologists/pharmacy/:regNo',
    name: 'PharmacyDermatologists',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: PharmacyDermatologists
  },
  {
    path: '/dermatologists/:email/:regNo/appointment',
    name: 'DermatologistAppointmentForm',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
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
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: PharmacyEdit
  },
  {
    path: '/pricing/pharmacy/:regNo',
    name: 'MedicineList',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: MedicineList
  },
  {
    path: '/pricing/medicine/:regNo/:code',
    name: 'MedicinePricing',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: MedicinePricing
  },
  {
    path: '/dermatologists/list/pharmacy/:regNo',
    name: 'PharmacyDermatologistList',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: PharmacyDermatologistList
  },
  {
    path: '/dermatologists/all',
    name: 'AllDermatologistList',
    component: AllDermatologistList
  },
  {
    path: '/pharmacists/list/pharmacy/:regNo',
    name: 'PharmacyPharmacistList',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: PharmacyPharmacistList
  },
  {
    path: '/pharmacists/all',
    name: 'AllPharmacistList',
    component: AllPharmacistList
  },
  {
    path: '/admin/pharmacy/account',
    name: 'PharmacyAdminAccount',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: PharmacyAdminAccount
  },
  {
    path: '/vacation/requests/:regNo',
    name: 'VacationRequestTable',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: VacationRequestTable
  },
  {
    path: '/vacation/response/:id',
    name: 'VacationResponseForm',
    beforeEnter: (to, from, next) => {
      let role = TokenDecoder.getUserRole()
      if (role != 'ROLE_PH_ADMIN')
        next({name: 'UserHomePage'})
      else
        next()
    },
    component: VacationResponseForm
  },
  {
    path: '/allmedicineordertable/:regNo',
    name: 'AllMedicineTable',
    component: AllMedicineTable
  },
  {
    path: '/orders/:regNo',
    name: 'OrderList',
    component: OrderList
  },
  {
    path: '/offers/:id',
    name: 'OffersList',
    component: OffersList
  },
  {
    path: '/discount/:regNo/:code',
    name: 'MedicineDiscount',
    component: MedicineDiscount
  },
  {
    path: '/employ/list/dermatologists/:regNo',
    name: 'EmployDermatologist',
    component: EmployDermatologist
  },
  {
    path: '/employ/dermatologist/:regNo/:email',
    name: 'EmployDermatologistForm',
    component: EmployDermatologistForm
  },
  {
    path: '/employ/list/pharmacists/:regNo',
    name: 'EmployPharmacist',
    component: EmployPharmacist
  },
  {
    path: '/employ/pharmacist/:regNo/:email',
    name: 'EmployPharmacistForm',
    component: EmployPharmacistForm
  },
  {
    path: '/reports/:regNo',
    name: 'IncomeReport',
    component: IncomeReport
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
