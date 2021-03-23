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
          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Home</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Account</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-calendar</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Appointments</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
  export default {
    name: "HomePagePatient",
    components: {
  },
    data: () => ({
      drawer: false,
      group: null,
    }),
  }
</script>