<template>
  <v-card
  class="text-center"
    color="white"
    flat
    height="200px"
    tile
    style="margin-left: 10px; margin-right: 10px;"
  >
  <v-navigation-drawer
      id="core-navigation-drawer"
      v-model="drawer"
      :expand-on-hover="expandOnHover"
      app
      flat
      width="260"
    >
      <v-list nav expand>
        <v-list-item-group v-model="group">
          <template v-for="item in items">
            <v-list-item :key="item.title" @click="show(item.to)">
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </template>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
      id="app-bar"
      app
      style="background-color: #00b2b2"
      
      height="75"
    >
      <v-btn
        class="mr-3"
        style="background-color: #00b2b2"
        elevation="0"
        fab
        small
        @click="drawer = !drawer"
      >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-btn>
      <v-toolbar-title>Patient</v-toolbar-title>
      <v-spacer></v-spacer>
       <v-btn text style="background-color: #00b2b2;" @click="logout()">
         <span>Sign out</span>
      </v-btn>
    </v-app-bar>

    <v-main fluid>
      <PharmaciesList v-if="show_menu==='pharmacies'"/>
      <Calendar v-if="show_menu==='calendar'"/>
      <AppointmenPharmacist v-if="show_menu==='appointment'"/>
      <PatientAccount v-if="show_menu==='acoount'"/>
      <MedicineOrder v-if="show_menu==='medicineOrder'"/>
      <MedicineList v-if="show_menu==='MedicineList'"/>
      <AppointmentsHistory v-if="show_menu==='history'"/>
      <PharmacistsAppointmentsHistory v-if="show_menu==='pharmacistHistory'"/>
      <Advertising v-if="show_menu==='advertising'"/>
      <Allergies v-if="show_menu==='addAllergies'"/>
      <Rating v-if="show_menu==='rating'"/>     
    </v-main>

  </v-card>
</template>

<script>
  import PharmaciesList from '../views/patient/PharmaciesList.vue'
  import Calendar from '../views/CalendarPatient.vue'
  import AppointmenPharmacist from '../views/patient/AppointmenPharmacist.vue'
  import PatientAccount from '@/components/PatientAccount'
  import MedicineOrder from '@/components/patient/MedicineOrder.vue'
  import MedicineList from '@/components/patient/MedicineList.vue'
  import AppointmentsHistory from '@/components/patient/AppointmentsHistory.vue'
  import Advertising from '@/components/patient/Advertising.vue'
  import Allergies from '@/components/patient/Allergies.vue'
  import Rating from '@/components/patient/Rating.vue'
  import PharmacistsAppointmentsHistory from '@/components/patient/PharmacistsAppointmentsHistory.vue'
  
  export default {
    name: "HomePagePatient",
    components: {
      PharmaciesList,
      Calendar,
      AppointmenPharmacist,
      PatientAccount, 
      MedicineOrder,
      MedicineList,
      AppointmentsHistory,
      Advertising,
      Allergies,
      PharmacistsAppointmentsHistory,
      Rating
    },
    props: {
    expandOnHover: {
      type: Boolean,
      default: false,
    },
  },
    data: () => ({
    drawer: false,
    group: null,
    show_menu:"calendar",
    items: [
      {
        icon: "mdi-home",
        title: "Home",
        to: "pharmacies",
      },
      {
        icon: "mdi-account",
        title: "Acoount",
        to: "acoount", //DermatologistAppointment/:regNo
      },
      {
        icon: "mdi-calendar",
        title: "Calendar",
        to: "calendar",
      },
      {
        icon: "mdi-plus",
        title: "Appointment",
        to: "appointment",
      },
      {
        icon: "mdi-plus",
        title: "Medicine order",
        to: "MedicineList",
      },
      {
        icon: "mdi-emoticon-angry-outline",
        title: "My orders",
        to: "medicineOrder",
      },
      {
        title: "History of dermatologist appointments",
        to: "history",
      },
      {
        title: "History of pharmacist appointments",
        to: "pharmacistHistory",
      },
      {
        title: "Advertising",
        to: "advertising",
      },
      {
        title: "Add allergies",
        to: "addAllergies",
      },
      {
        title: "Rating",
        to: "rating",
      },
    ],
  }),
  methods: {
    show(param){
      this.show_menu=param;
      this.drawer=false;
    },
    onScheduleClick:function(check){
      this.show_menu=check;
      this.drawer=false;
    },
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/");
    }
  }
};
</script>