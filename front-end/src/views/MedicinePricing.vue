<template>
  <div>
    <pharmacy-admin-home>
    </pharmacy-admin-home>
    <v-card class="mx-auto" justify="center" style="width: 50%">
        <v-app-bar dark color="dark cyan" prominent>
            <v-toolbar-title>Uredite cenovnik leka</v-toolbar-title>
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
            <v-btn class="submit-bttn" @click="submitPricing">Izmenite cenovnik</v-btn>
            <hr />
            <div>
                <h2>Trenutni cenovnik leka</h2>
                <p style="font-size:1.5vw">Cena: {{this.currentPricing.price}}</p>
                <p style="font-size:1.5vw">Važi od: {{this.currentPricing.pricingStart.substring(0, 10)}}</p>
            </div>
            <hr />
            <h2>Prethodni cenovnici leka</h2>
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
            })
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
            .post(path, pricing)
            .then(response => {
                alert('Uspešno promenjen cenovnik leka')
                console.log(response.data)
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