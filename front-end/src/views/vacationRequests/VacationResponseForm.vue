<template>
  <div>
    <pharmacy-admin-home></pharmacy-admin-home>
    <v-row justify="center">
        <v-col cols="12" sm="10" md="8" lg="8">
            <v-card>
                <v-app-bar color="dark cyan" dark prominent flat>
                    <v-toolbar-title>
                        Vacation Request
                    </v-toolbar-title>
                </v-app-bar>
                <v-spacer></v-spacer>
                <v-card-text>
                    <h4 class="display-1 text--primary">Requested by: {{request.medicalWorkerId}}</h4>
                    <br />
                    <h5 class="display-1">Vacation is from</h5>
                    <p class="display-1">{{request.startDate}} -- {{request.endDate}}</p>
                    <template v-if="showTextArea">
                        <hr />
                        <br />
                        <v-textarea solo name="input-7-4" label="Reason for declining..."
                            v-model="declinationText"></v-textarea>
                        <v-btn @click="sendDecline">Send response</v-btn>
                        <br />
                        <br />
                        <hr />
                        <br />
                    </template>
                    <v-btn @click="acceptRequest">Accept</v-btn>
                    <v-btn @click="declineRequest">Decline</v-btn>
                </v-card-text>
            </v-card>
        </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from '../PharmacyAdminHome.vue'
import authHeader from '../../services/auth-header'
export default {
  components: { PharmacyAdminHome },
  name: 'VacationResponseForm',
  data() {
      return {
          request: {},
          showTextArea: false,
          declinationText: ''
      }
  },
  mounted() {
      let path = 'http://localhost:9090/api/vaccation/pharmacist/request/' + this.$route.params.id
      axios
      .get(path, { headers: authHeader() })
      .then(response => {
          this.request = response.data
      })
      .catch(error => {
          alert(error.data.response)
      })
  },
  methods: {
      declineRequest() {
          this.showTextArea = true
      },
      acceptRequest() {
          this.declinationText = ''
          this.showTextArea = false
          axios
          .put('http://localhost:9090/api/vaccation/pharmacist/approve/request', {MedicalWorkerID: this.$route.params.id})
          .then(response => {
              console.log(response)
              alert('Succesfully approved request')
              this.$router.push('/pharmacyadmin')
          })
          .catch(error => {
              alert(error.response.data)
          })
      },
      sendDecline() {
          alert('Declining because of: ' + this.declinationText)
          axios
          .put('http://localhost:9090/api/vaccation/pharmacist/decline/request', {
              MedicalWorkerID: this.$route.params.id,
              text: this.declinationText
          }, { headers: authHeader()})
          .then(response => {
              console.log(response)
              alert('Succesfully declined request')
              this.$router.push('/pharmacyadmin')
          })
          .catch(error => {
              alert('Error')
              console.log(error.response.data)
          })
      }
  }
}
</script>

<style>

</style>