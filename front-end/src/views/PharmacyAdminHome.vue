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
          <v-list-item :to="'/edit/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>Izmeni apoteku</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/dermatologists/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>Novi termin (dermatolog)</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Izveštaji</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/pharmacists/list/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>Farmaceuti</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/dermatologists/list/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>Dermatolozi</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/pricing/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>Lekovi</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Upiti za lekove</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Narudžbenice za lekove</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Nova narudžbenica</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Akcije/Promocije</v-list-item-title>
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
  import axios from 'axios'
  import authHeader from '../services/auth-header'
  import TokenDecoder from '../services/token-decoder'
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
      pharmacy: { },
    }),
    mounted () {
      let adminEmail = TokenDecoder.getUserEmail()
      let path = 'http://localhost:9090/api/pharmacy/admin/' + adminEmail
      axios
      .get(path, { headers: authHeader() })
      .then(response => {
        this.pharmacy = response.data
      })
      .catch(response => {
        console.log('Admin toolbar: ')
        console.log(response)
        this.$router.push('/')
      })
    },
  }
</script>