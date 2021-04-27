<template>

  <div>
    <v-toolbar
      style="background-color:darkcyan;">
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

    <v-toolbar-title>System Administrator</v-toolbar-title>
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
          <v-list-item @click="write">
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Home</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon 
            style="color:Tomato;">mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Account</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-pharmacy</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Add new pharmacy</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-doctor</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Add new dematologist</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-truck-delivery</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Add new supplier</v-list-item-title>
          </v-list-item>
          
          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-hospital-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Registered drugs</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-medical-bag</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Add new drug</v-list-item-title>
          </v-list-item>  

         <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-emoticon-angry-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Complaints</v-list-item-title>
          </v-list-item>
 
          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color:Tomato;">mdi-account-supervisor-circle</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color:Tomato;">Add new administrator</v-list-item-title>
          </v-list-item>


        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    
  </div>
  
  
</template>

<script>
  export default {
    name: "SystemAdminHome",
    components: {
 
  },
    data: () => ({
      drawer: false,
      group: null,
    }),
    methods: {
      write() {
        alert('a')
      }
    }
  }
</script>
