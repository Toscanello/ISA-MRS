<template>
  <v-row justify="center">
    <v-col
      cols="12"
      sm="10"
      md="8"
      lg="6"
    >
      <v-card ref="form">
        <v-app-bar 
          color="dark cyan"
          dark
          prominent
          flat>
          <v-toolbar-title>Izmena podataka</v-toolbar-title>
        </v-app-bar>
        <v-spacer></v-spacer>
        <v-card-text>
          <v-text-field
            ref="name"
            v-model="pharmacy.name"
            :rules="[() => !!pharmacy.name || 'This field is required']"
            :error-messages="errorMessages"
            label="Naziv apoteke"
            required
          ></v-text-field>
          <v-text-field
            ref="address"
            v-model="pharmacy.address.street"
            :rules="[
              () => !!pharmacy.address.street || 'This field is required',
              () => !!pharmacy.address.street && pharmacy.address.street.length <= 25 || 'Address must be less than 25 characters',
              addressCheck
            ]"
            label="Adresa"
            counter="25"
            required
          ></v-text-field>
          <v-text-field
            ref="street-no"
            v-model="pharmacy.address.streetNumber"
            :rules="[() => !!pharmacy.address.streetNumber || 'This field is required']"
            label="Broj ulice"
            required
          ></v-text-field>
          <v-text-field
            ref="place"
            v-model="pharmacy.address.place"
            :rules="[() => !!pharmacy.address.place || 'This field is required', addressCheck]"
            label="Mesto"
            required
          ></v-text-field>
          <v-autocomplete
            ref="country"
            v-model="pharmacy.address.country"
            :rules="[() => !!pharmacy.address.country || 'This field is required']"
            :items="countries"
            label="Država"
            required
          ></v-autocomplete>
          <GmapMap
            id='map'
            :center="{lat:this.marker.position.lat, lng:this.marker.position.lng}"
            :zoom="15"
            map-type-id="terrain"
            style="width: 100%; height: 500px"
            @click='mark'
            ><GmapMarker
                :key="index"
                :position="marker.position"
                :clickable="true"
                :draggable="true"
                @click="center=m.position"
            />
          </GmapMap>
        </v-card-text>
        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
          <v-btn text>
            Cancel
          </v-btn>
          <v-spacer></v-spacer>
          <v-slide-x-reverse-transition>
            <v-tooltip
              v-if="formHasErrors"
              left
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  icon
                  class="my-0"
                  v-bind="attrs"
                  @click="resetForm"
                  v-on="on"
                >
                  <v-icon>mdi-refresh</v-icon>
                </v-btn>
              </template>
              <span>Refresh form</span>
            </v-tooltip>
          </v-slide-x-reverse-transition>
          <v-btn
            color="primary"
            text
            @click="submit"
          >
            Submit
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'PharmacyEdit',
    data: () => ({
      countries: ['Afghanistan', 'Albania', 'Algeria', 'Andorra', 'Angola', 'Anguilla', 'Antigua &amp; Barbuda', 'Argentina', 'Armenia', 'Aruba', 'Australia', 'Austria', 'Azerbaijan', 'Bahamas', 'Bahrain', 'Bangladesh', 'Barbados', 'Belarus', 'Belgium', 'Belize', 'Benin', 'Bermuda', 'Bhutan', 'Bolivia', 'Bosnia &amp; Herzegovina', 'Botswana', 'Brazil', 'British Virgin Islands', 'Brunei', 'Bulgaria', 'Burkina Faso', 'Burundi', 'Cambodia', 'Cameroon', 'Cape Verde', 'Cayman Islands', 'Chad', 'Chile', 'China', 'Colombia', 'Congo', 'Cook Islands', 'Costa Rica', 'Cote D Ivoire', 'Croatia', 'Cruise Ship', 'Cuba', 'Cyprus', 'Czech Republic', 'Denmark', 'Djibouti', 'Dominica', 'Dominican Republic', 'Ecuador', 'Egypt', 'El Salvador', 'Equatorial Guinea', 'Estonia', 'Ethiopia', 'Falkland Islands', 'Faroe Islands', 'Fiji', 'Finland', 'France', 'French Polynesia', 'French West Indies', 'Gabon', 'Gambia', 'Georgia', 'Germany', 'Ghana', 'Gibraltar', 'Greece', 'Greenland', 'Grenada', 'Guam', 'Guatemala', 'Guernsey', 'Guinea', 'Guinea Bissau', 'Guyana', 'Haiti', 'Honduras', 'Hong Kong', 'Hungary', 'Iceland', 'India', 'Indonesia', 'Iran', 'Iraq', 'Ireland', 'Isle of Man', 'Israel', 'Italy', 'Jamaica', 'Japan', 'Jersey', 'Jordan', 'Kazakhstan', 'Kenya', 'Kuwait', 'Kyrgyz Republic', 'Laos', 'Latvia', 'Lebanon', 'Lesotho', 'Liberia', 'Libya', 'Liechtenstein', 'Lithuania', 'Luxembourg', 'Macau', 'Macedonia', 'Madagascar', 'Malawi', 'Malaysia', 'Maldives', 'Mali', 'Malta', 'Mauritania', 'Mauritius', 'Mexico', 'Moldova', 'Monaco', 'Mongolia', 'Montenegro', 'Montserrat', 'Morocco', 'Mozambique', 'Namibia', 'Nepal', 'Netherlands', 'Netherlands Antilles', 'New Caledonia', 'New Zealand', 'Nicaragua', 'Niger', 'Nigeria', 'Norway', 'Oman', 'Pakistan', 'Palestine', 'Panama', 'Papua New Guinea', 'Paraguay', 'Peru', 'Philippines', 'Poland', 'Portugal', 'Puerto Rico', 'Qatar', 'Reunion', 'Romania', 'Russia', 'Rwanda', 'Saint Pierre &amp; Miquelon', 'Samoa', 'San Marino', 'Satellite', 'Saudi Arabia', 'Senegal', 'Serbia', 'Seychelles', 'Sierra Leone', 'Singapore', 'Slovakia', 'Slovenia', 'South Africa', 'South Korea', 'Spain', 'Sri Lanka', 'St Kitts &amp; Nevis', 'St Lucia', 'St Vincent', 'St. Lucia', 'Sudan', 'Suriname', 'Swaziland', 'Sweden', 'Switzerland', 'Syria', 'Taiwan', 'Tajikistan', 'Tanzania', 'Thailand', `Timor L'Este`, 'Togo', 'Tonga', 'Trinidad &amp; Tobago', 'Tunisia', 'Turkey', 'Turkmenistan', 'Turks &amp; Caicos', 'Uganda', 'Ukraine', 'United Arab Emirates', 'United Kingdom', 'United States', 'Uruguay', 'Uzbekistan', 'Venezuela', 'Vietnam', 'Virgin Islands (US)', 'Yemen', 'Zambia', 'Zimbabwe'],
      errorMessages: '',
      name: null,
      address: null,
      place: null,
      streetNo: null,
      country: null,
      formHasErrors: false,
      marker: {position: {lat:0, lng: 0}},
      pharmacy: null,
    }),

    computed: {
      form () {
        return {
          name: this.pharmacy.name,
          address: this.pharmacy.address.street,
          place: this.pharmacy.address.place,
          streetNo: this.pharmacy.address.streetNumber,
          country: this.pharmacy.address.country,
          lat: this.marker.position.lat,
          lng: this.marker.position.lng
        }
      },
    },

    watch: {
      name () {
        this.errorMessages = ''
      },
    },

    methods: {
      addressCheck () {
        this.errorMessages = this.address && !this.name
          ? `Hey! I'm required`
          : ''

        return true
      },
      resetForm () {
        this.errorMessages = []
        this.formHasErrors = false

        Object.keys(this.form).forEach(f => {
          this.$refs[f].reset()
        })
      },
      submit () {
        this.formHasErrors = false

        Object.keys(this.form).forEach(f => {
          if (!this.form[f]) this.formHasErrors = true

          //this.$refs[f].validate(true)
        })
        if (this.formHasErrors) alert('Ne valja')
        else {
            this.pharmacy.address.location.geoHeight = this.marker.position.lat
            this.pharmacy.address.location.geoWidth = this.marker.position.lng
            alert(JSON.stringify(this.pharmacy))
        }
      },
      mark(event) {
        console.log(event.latLng.lat())
        console.log(event.latLng.lng())
        this.marker.position.lat = event.latLng.lat()
        this.marker.position.lng = event.latLng.lng()
      }
    },
    mounted() {
        let path = "http://localhost:9090/api/pharmacy/" + this.$route.params.regNo
        axios
        .get(path)
        .then(response => {
        this.pharmacy = response.data
        this.name = this.pharmacy.name
        this.address = this.pharmacy.address.street
        this.streetNo = this.pharmacy.address.streetNumber
        this.place = this.pharmacy.address.place
        this.country = 'Serbia'
        this.marker.position.lat = Number(this.pharmacy.address.location.geoHeight)
        this.marker.position.lng = Number(this.pharmacy.address.location.geoWidth)
        })
    }
  }
</script>

<style>

</style>