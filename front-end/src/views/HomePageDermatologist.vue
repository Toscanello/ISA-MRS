<template>
  <v-container>
    <v-navigation-drawer
      id="core-navigation-drawer"
      v-model="drawer"
      :expand-on-hover="expandOnHover"
      app
      width="260"
    >
      <v-list nav expand>
        <v-list-item-group v-model="group">
          <template v-for="item in items">
            <v-list-item :key="item.title" :to="item.to">
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title >{{ item.title }}</v-list-item-title>
            </v-list-item>
          </template>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
      id="app-bar"
      absolute
      app
      style="background-color: darkcyan"
      flat
      height="75"
    >
      <v-btn
        class="mr-3"
        style="background-color: darkcyan"
        elevation="0"
        fab
        small
        @click="drawer = !drawer"
      >
        <v-icon> mdi-dots-vertical </v-icon>
      </v-btn>
      <v-toolbar-title>Dermatologist</v-toolbar-title>
    </v-app-bar>
    <v-container>
      <h1 style="background-color: darkcyan">Patients</h1>
      <PatientSearch id="search" @clicked="onSearchClick" />
      <PatientSort id="search" @clicked="onSortClick" />
      <v-row class="l-5">
        <v-col v-for="i in patients" :key="i.email" sm="6" lg="3">
          <v-card shaped>
            <v-list dense>
              <v-list-item>
                <v-list-item-content>Email:</v-list-item-content>
                <v-list-item-content class="align-end">
                  {{ i.email }}
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>Name:</v-list-item-content>
                <v-list-item-content class="align-end">
                  {{ i.name }}
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>Surname:</v-list-item-content>
                <v-list-item-content class="align-end">
                  {{ i.surname }}
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import axios from "axios";
import PatientSearch from "@/components/PatientSearch.vue";
import PatientSort from "@/components/PatientSort.vue";

export default {
  name: "HomePageDermatologist",
  components: {
    PatientSearch,
    PatientSort,
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
    patients: [],
    items: [
      {
        icon: "mdi-home",
        title: "Home",
        to: "/",
      },
      {
        icon: "mdi-account",
        title: "Acoount",
        to: "/",
      },
      {
        icon: "mdi-pencil",
        title: "Start appointment",
        to: "/",
      },
      {
        icon: "mdi-calendar",
        title: "Calendar",
        to: "/",
      },
      {
        icon: "mdi-plus",
        title: "Vaccation",
        to: "/",
      },
      {
        icon: "mdi-plus",
        title: "New appointment",
        to: "/dermatologists/derm1@gmail.com/abc/appointment",
      },
    ],
  }),
  created() {
    axios.get("http://localhost:9090/patients/derm1@gmail.com").then((resp) => {
      this.patients = resp.data;
    });
  },
  methods: {
    onSearchClick: function (search) {
      console.log(search);
      axios
        .get("http://localhost:9090/patients/search", {
          params: {
            email: "derm1@gmail.com",
            name: search.name,
            surname: search.surname,
          },
        })
        .then((response) => (this.patients = response.data));
    },
    onSortClick: function (sorting) {
      console.log(sorting);
      if (sorting == "NameAsc") {
        this.patients.sort(function (a, b) {
          if (a.name < b.name) {
            return -1;
          }
          if (a.name > b.name) {
            return 1;
          }
          return 0;
        });
      } else if (sorting == "NameDesc") {
        this.patients.sort(function (a, b) {
          if (a.name < b.name) {
            return 1;
          }
          if (a.name > b.name) {
            return -1;
          }
          return 0;
        });
      } else if (sorting == "SurnameAsc") {
        this.patients.sort(function (a, b) {
          if (a.surname < b.surname) {
            return -1;
          }
          if (a.surname > b.surname) {
            return 1;
          }
          return 0;
        });
      } else if (sorting == "SurnameDesc") {
        this.patients.sort(function (a, b) {
          if (a.surname < b.surname) {
            return -1;
          }
          if (a.surname > b.surname) {
            return 1;
          }
          return 0;
        });
      }
    },
  },
};
</script>

<style scoped>
</style>