<template>
    <v-card>
    <v-toolbar
      color="cyan"
      flat
      dark
      style="background-color: darkcyan"
    >
      <v-toolbar-title>PROFIL APOTEKE</v-toolbar-title>
    </v-toolbar>
    <v-tabs vertical>
      <v-tab>
        Opste informacije
      </v-tab>
      <v-tab>
        Lekovi i zakazivanje
      </v-tab>

      <v-tab-item>
        <v-card flat>
          <v-card-text v-if="is_data_fetched">
            <div style="display: flex; align-items: top">
              <div class="info">
                <h1>{{pharmacy.name.toUpperCase()}}</h1>

                <p style="font-weight: bold;">
                  Prosecna ocena: {{rating}}
                </p>

                <p>
                  {{fullAddress}}
                </p>
              </div>
            
              <GmapMap
                :center="{
                  lat: pharmacy.address.location.geoHeight,
                  lng: pharmacy.address.location.geoWidth
                  }"
                :zoom="9"
                style="width:640px; height: 360px">
                <GmapMarker
                  :position="{
                  lat: pharmacy.address.location.geoHeight,
                  lng: pharmacy.address.location.geoWidth
                  }"></GmapMarker>
              </GmapMap>
            </div>
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>
            <table class="links">
                <tr>
                    <td><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ">Zaposleni</a></td>
                </tr>
                <tr>
                    <td><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ">Pretraga lekova</a></td>
                </tr>
                <tr>
                    <td><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ">Zakazite termin</a></td>
                </tr>
            </table>
          </v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs>
  </v-card>
</template>

<script>
import axios from 'axios'
export default {
    name: 'Pharmacy',
    data () {
      return {
        pharmacy: null,
        is_data_fetched: false
      }
    },
    mounted() {
      let path = "http://localhost:9090/pharmacies/" + this.$route.params.regNo
      axios
      .get(path)
      .then(response => {
        this.pharmacy = response.data
        console.log(JSON.stringify(this.pharmacy))
        this.is_data_fetched = true
        })
      .catch(error => {
        alert('Error: status ' + error.response.status)
        this.$router.push('/')
      })
    },
    computed: {
      fullAddress () {
        return this.pharmacy.address.street + ', ' +
        this.pharmacy.address.place + ', ' +
        this.pharmacy.address.country
      },
      rating() {
        let avgRating = 0;
        console.log(this.pharmacy.ratings.length)
        for (const rating of this.pharmacy.ratings) {
          avgRating += Number(rating);
        }
        if (this.pharmacy.ratings.length == 0)
          return avgRating;
        else
          return avgRating / this.pharmacy.ratings.length
      }
    }
}
</script>

<style scoped>
    table {
        font-size: 20px;
        text-align: left;
    }

    .links td {
      padding: 5px;
    }

    .links a:hover {
      position: relative;
      bottom: 3px;
      right: 3px;
    }

    tr {
        margin: 5px;
    }

    a {
        color: gray;
        text-decoration: none;
    }

    body {
      align-content:flex-end;
    }

    .info {
      margin: 30px;
      padding: 15px;
      border: 2px solid gainsboro;
      border-top-left-radius: 20px;
      border-bottom-right-radius: 20px;
    }

    .info:hover {
      position: relative;
      bottom: 5px;
      box-shadow: 5px 5px 5px;
    }
</style>