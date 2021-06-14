<template>
    <div>
        <pharmacy-admin-home></pharmacy-admin-home>
        <v-row justify="center">
            <v-col cols="12" sm="10" md="8" lg="8">
                <v-data-table
                    :headers="headers"
                    :items="offers"
                    :items-per-page="10"
                    class="elevation-1"
                >
                    <template v-slot:item.actions="{ item }">
                        <v-icon small class="mr-2" @click="acceptOffer(item)">
                            mdi-send-check
                        </v-icon>
                    </template>
                </v-data-table>
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
  name: 'OffersList',
  data() {
      return {
          offers: [],
          headers: [
              {text:'Supplier', value: 'supplier'},
              {text: 'Price', value: 'price'},
              {text: 'Available on', value: 'availableOnDate'},
              {text: 'Status', value: 'status'},
              {text: 'Accept', value: 'actions'}
          ]
      }
  },
  mounted() {
      let path = 'http://localhost:9090/api/order/offers/' + this.$route.params.id
      axios
      .get(path, { headers: authHeader() })
      .then(response => {
          this.offers = response.data
      })
      .catch(error => {
          alert(error.response.data)
      })
  },
  methods: {
      acceptOffer(item) {
          let path = 'http://localhost:9090/api/order/offer/accept/' + item.order_id + '/' + item.id
          axios
          .put(path, null, { headers: authHeader() })
          .then(response => {
              console.log(response.data)
              alert('Succesfully accepted order')
              this.$router.go()
          })
          .catch(error => {
              alert(error.message)
          })
      }
  }
}
</script>

<style>

</style>