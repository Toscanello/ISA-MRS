<template>
  <div id="wrapper">
      <div class="dermatologist-card" 
      :key="dermatologist.email" v-for="dermatologist in dermatologists">
          <h3>{{dermatologist.name}} {{dermatologist.surname}}</h3>
          <p>{{dermatologist.email}}</p>
          <v-btn depressed @click="goToAppointment(dermatologist.email)">Zakazi</v-btn>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'PharmacyDermatologists',
    data () {
        return {
            dermatologists: null
        }
    },
    methods: {
        goToAppointment: function(email) {
            regNo = this.$route.params.regNo
            this.$router.push('/dermatologists/' + email + '/' + regNo + '/appointment')
        }
    },
    mounted() {
        let path = "http://localhost:9090/api/dermatologists/pharmacy/" + this.$route.params.regNo
        axios
        .get(path)
        .then(response => {
            this.dermatologists = response.data
            console.log(JSON.stringify(this.dermatologists))
            })
        .catch(error => {
            alert('Error: status ' + error.response.status)
            this.$router.push('/')
        })
    }
}
</script>

<style>
    .dermatologist-card {
        border: 2px solid darkcyan;
        border-top-left-radius: 15px;
        border-bottom-right-radius: 15px;
        color: darkcyan;
        padding: 15px;
        margin: 15px;
        width: 50%;
    }

    .dermatologist-card h3 {
        padding-bottom: 20px;
        font-weight: bold;
    }
</style>
