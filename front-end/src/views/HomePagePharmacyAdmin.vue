<template>
  <div>
      <pharmacy-admin-home></pharmacy-admin-home>
      <div class="content-wrapper mx-auto">
        <br />
        <h1>Administracija apoteke</h1>
        <h1>{{pharmacy.name}}</h1>
        <br />
        <br />
        <br />
        <br />
        <GmapMap
            :center="{
            lat: pharmacy.address.location.geoHeight,
            lng: pharmacy.address.location.geoWidth
            }"
            :zoom="9"
            style="width:640px; height: 360px"
            class="rounded mx-auto d-block">
            <GmapMarker
            :position="{
            lat: pharmacy.address.location.geoHeight,
            lng: pharmacy.address.location.geoWidth
            }"></GmapMarker>
        </GmapMap>
        <br />
        <h3>{{pharmacy.address.street}} {{pharmacy.address.streetNumber}}</h3>
        <h3>{{pharmacy.address.place}}</h3>
        <br />
        <p>{{new Date().toJSON().slice(0,10)}} {{new Date().toJSON().slice(11,16)}}</p>
      </div>
  </div>
</template>

<script>
import PharmacyAdminHome from './PharmacyAdminHome.vue'
import axios from 'axios'
import TokenDecoder from '../services/token-decoder'
export default {
  components: { PharmacyAdminHome },
  name: 'HomePagePharmacyAdmin',
  data () {
      return {
          pharmacy: { }
      }
  },
  mounted () {
      let adminEmail = TokenDecoder.getUserEmail()
      axios
      .get('http://localhost:9090/api/pharmacy/admin/' + adminEmail)
      .then(response => {
        this.pharmacy = response.data
      })
      .catch(response => {
        console.log(response)
        this.$router.push('/')
      })
    },
}
</script>

<style scoped>
  .content-wrapper {
    width: 60%;
    border: 2px solid #efefef;
    border-radius: 15px;
    margin: 15px;
  }
</style>