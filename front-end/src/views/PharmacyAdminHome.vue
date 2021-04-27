<template>

  <div>
    <v-toolbar style="background-color:#00bcd4; color: white;">
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
      <v-toolbar-title>Administrator apoteke</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon @click="logout">
        <v-icon>mdi-export</v-icon>
      </v-btn>
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
          <v-list-item to="edit/pharmacy/abc">
            <v-list-item-title style="color:Tomato;">Izmeni apoteku</v-list-item-title>
          </v-list-item>

          <v-list-item to="/dermatologists/pharmacy/abc">
            <v-list-item-title style="color:Tomato;">Novi termin (dermatolog)</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title style="color:Tomato;">Izveštaji</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title style="color:Tomato;">Farmaceuti</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title style="color:Tomato;">Dermatolozi</v-list-item-title>
          </v-list-item>

          <v-list-item to="/pricing/pharmacy/abc">
            <v-list-item-title style="color:Tomato;">Lekovi</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title style="color:Tomato;">Upiti za lekove</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title style="color:Tomato;">Narudžbenice za lekove</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title style="color:Tomato;">Nova narudžbenica</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title style="color:Tomato;">Akcije/Promocije</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title style="color:Tomato;">Izmeni profil</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    
  </div>
  
  
</template>

<script>
  export default {
    name: "PharmacyAdminHome",
    components: { },
    methods: {
      logout() {
        localStorage.removeItem('user')
        this.$router.push('/')
      }
    },
    data: () => ({
      drawer: false,
      group: null,
    }),
  }
</script>
