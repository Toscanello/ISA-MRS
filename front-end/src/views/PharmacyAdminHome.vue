<template>

  <div>
    <v-toolbar style="background-color:#00bcd4; color: white;">
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
      <v-toolbar-title>Pharmacy admin</v-toolbar-title>

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
          <v-list-item to='/pharmacyadmin'>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/edit/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>Modify pharmacy</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/dermatologists/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>New appointment (dermatologist)</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/reports/' + pharmacy.regNo">
            <v-list-item-title>Reports</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/pricing/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>MEdicine pricing</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/dermatologists/list/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>Dermatologists</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/pharmacists/list/pharmacy/' + pharmacy.regNo">
            <v-list-item-title>Pharmacists</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/employ/list/dermatologists/' + pharmacy.regNo">
            <v-list-item-title>Hire dermatologist</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/employ/list/pharmacists/' + pharmacy.regNo">
            <v-list-item-title>Hire pharmacist</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>Medicine inquiry</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/orders/' + pharmacy.regNo">
            <v-list-item-title>Medicine order</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/allmedicineordertable/' + pharmacy.regNo">
            <v-list-item-title>New order</v-list-item-title>
          </v-list-item>

          <v-list-item :to="'/vacation/requests/' + pharmacy.regNo">
            <v-list-item-title>Vaccation requests</v-list-item-title>
          </v-list-item>

          <v-list-item to="/admin/pharmacy/account">
            <v-list-item-title>Account</v-list-item-title>
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