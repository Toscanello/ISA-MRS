<template>
  <div>
    <v-toolbar style="background-color:darkcyan;">
    <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
    <v-toolbar-title>Title</v-toolbar-title>
    </v-toolbar>

    <v-navigation-drawer
      v-model="drawer"
      app
      temporary
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
        >
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
  </div>
</template>

<script>
  export default {
    name: "HomePagePatient",
    data: () => ({
      drawer: false,
      group: null,
      items: [
      {
        icon: "mdi-home",
        title: "Home",
        to: "/pharmacies-list",
      },
      {
        icon: "mdi-account",
        title: "Acoount",
        to: "/",
      },
      {
        icon: "mdi-calendar",
        title: "Calendar",
        to: "/calendar",
      },
      {
        icon: "mdi-hospital-box",
        title: "Drugs",
        to: "/",
      },
      {
        icon: "mdi-prescription",
        title: "Prescription",
        to: "/",
      },
      {
        icon: "mdi-plus",
        title: "Appointment",
        to: "/appointment-pharmacist",
      },
      {
        icon: "mdi-plus",
        title: "Allegries",
        to: "/",
      },
      {
        icon: "mdi-emoticon-angry-outline",
        title: "Complaints",
        to: "/",
      },
    ],
    }),
  }
</script>