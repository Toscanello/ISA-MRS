<template>
  <div>
    <pharmacy-admin-home>
    </pharmacy-admin-home>
    <v-card class="mx-auto" justify="center" style="width: 50%">
        <v-app-bar dark color="dark cyan" prominent>
            <v-toolbar-title>Arrange pricing</v-toolbar-title>
        </v-app-bar>
        <v-container>
            <v-date-picker v-model="startDate" color="dark cyan"></v-date-picker>
            <div id="price">
                <v-text-field
                    ref="price"
                    v-model="price"
                    :rules="[() => !!price && price > 0|| 'Polje je obavezno']"
                    label="Unesite cenu leka"
                    value="1000"
                    filled
                ></v-text-field>
            </div>
            <v-btn class="submit-bttn" @click="submitPricing">Change pricing</v-btn>
            <hr />
            <div v-if="currentPricing">
                <h2>Current pricing</h2>
                <p style="font-size:1.5vw">Price: {{this.currentPricing.price}}</p>
                <p style="font-size:1.5vw">Date from: {{this.currentPricing.pricingStart.substring(0, 10)}}</p>
            </div>
            <div v-else> 
                <h2>Medicine doesn't have pricing</h2>
            </div>
            <hr />
            <h2>Former pricings</h2>
            <v-col cols="12"
                        v-for="(pastPricing, index) in inactivePricings"
                        :key="index">
                        <v-card>
                            <div class="d-flex flex-no-wrap justify-space-between">
                                <div>
                                    <v-card-title
                                    class="text-h5"
                                    v-text="pastPricing.price"></v-card-title>

                                    <v-card-subtitle class="text-h6" v-text="pastPricing.pricingStart.substring(0, 10) + ' | ' + pastPricing.pricingEnd.substring(0, 10)"></v-card-subtitle>
                                </div>
                            </div>
                        </v-card>
                    </v-col>
        </v-container>
    </v-card>
  </div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from './PharmacyAdminHome.vue'
import authHeader from '../services/auth-header'
export default {
  components: { PharmacyAdminHome },
    name: 'MedicinePricing',
    data () {
        return {
            startDate: '',
            price: 0,
            pastPricings: []
        }
    },
    computed: {
        currentPricing () {
            return this.pastPricings.find(function(pricing) { 
                return pricing.pricingEnd == null
                })
        },
        inactivePricings() {
            return this.pastPricings.filter(function(pricing) {
                return pricing.pricingEnd != null
            }).sort((a, b) => (a.pricingEnd > b.pricingEnd) ? -1 : 1)
        },
        startDateTime() {
            return this.startDate + ' ' + '00:00'
        }
    }, 
    mounted () {
        let addedPath = this.$route.params.regNo + '/' + this.$route.params.code
        let path = 'http://localhost:9090/api/medicine/pricing/' + addedPath
        console.log(path)
        axios
        .get(path)
        .then(response => {
            this.pastPricings = response.data
        })
        .catch(response => {
            console.log(response.data)
        })
    },
    methods: {
        submitPricing() {
            if (this.currentPricing && this.startDateTime < this.currentPricing.pricingStart) {
                alert('Novi cenovnik ne može početi pre trenutnog!')
                return
            }
            
            alert('Ovaj lek će imati cenu ' + this.price + ' počevši od datuma ' + this.startDate)

            let pricing = {
                price: this.price,
                pricingStart: this.startDateTime,
                pricingEnd: null,
                pharmacy: this.currentPricing.pharmacy,
                medicine: this.currentPricing.medicine
            }
            let path = 'http://localhost:9090/api/medicine/set/pricing/'
            let addedPath = this.$route.params.regNo + '/' + this.$route.params.code
            path = path + addedPath
            console.log(path)
            
            axios
            .post(path, pricing, { headers: authHeader() })
            .then(response => {
                alert('Uspešno promenjen cenovnik leka')
                console.log(response.data)
                this.$router.go()
            })
            .catch(response => {
                console.log(response.data)
            })
        }
    }
}
</script>

<style>
    .submit-bttn {
        margin: 10px;
    }
</style>