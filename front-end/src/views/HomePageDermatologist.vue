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
            <v-list-item :key="item.title" @click="show(item.to)">
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
    <v-main fluid>
      <PatientList v-if="show_menu==='patients'"/>
    </v-main>
  </v-container>
</template>

<script>
import PatientList from "@/components/PatientList.vue";

export default {
  name: "HomePageDermatologist",
  components: {
    PatientList
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
    show_menu: "patients",
    items: [
      {
        icon: "mdi-home",
        title: "Home",
        to: "patients",
      },
      {
        icon: "mdi-account",
        title: "Acoount",
        to: "patients",
      },
      {
        icon: "mdi-pencil",
        title: "Start appointment",
        to: "patients",
      },
      {
        icon: "mdi-calendar",
        title: "Calendar",
        to: "patients",
      },
      {
        icon: "mdi-plus",
        title: "Vaccation",
        to: "patients",
      },
      {
        icon: "mdi-plus",
        title: "New appointment",
        to: "patients",
      },
    ],
  }),
  created() {
    localStorage.setItem('korisnik','derm1');
  },
  methods: {
    show(param){
      this.show_menu=param;
      this.drawer=false;
    },
  }
};
</script>

<style scoped>
</style>