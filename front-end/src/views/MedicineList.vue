<template>
  <!-- Na ovu foru: https://vuetifyjs.com/en/components/cards/#grids -->
    <v-card class="mx-auto">
        <v-app-bar dark color="dark cyan" prominent>
            <v-toolbar-title>Lista lekova apoteke</v-toolbar-title>
        </v-app-bar>
        <v-container>
            <v-row dense>
                <v-col cols="12"
                    v-for="(med, index) in medicine"
                    :key="index">
                    <v-card>
                        <div class="d-flex flex-no-wrap justify-space-between">
                            <div>
                                <v-card-title
                                class="text-h5"
                                v-text="med.code + ' | ' + med.name"></v-card-title>

                                <v-card-subtitle class="text-h6" v-text="'Proizvodjac: ' + med.manufacturer + ', Sastav: ' + med.composition + ', Forma: ' + med.drugForm"></v-card-subtitle>

                                <v-card-actions>
                                    <v-btn @click="viewPricing(med.code)">Pregled cenovnika</v-btn>
                                    <v-btn outlined rounded small>Obrisi iz ponude</v-btn>
                                </v-card-actions>
                            </div>
                        </div>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-card>
</template>

<script>
import axios from 'axios'
export default {
    name: 'MedicineList',
    data () {
        return {
            medicine: []
        }
    },
    mounted () {
        axios
        .get("http://localhost:9090/api/medicine/pharmacy/" + this.$route.params.regNo)
        .then(response => {
            this.medicine = response.data
        })
        .catch(response => {
            console.log('Error ' + response.data)
        })
    },
    methods: {
        viewPricing(code) {
            this.$router.push('/pricing/medicine/' + this.$route.params.regNo + '/' + code)
        }
    }
}
</script>

<style>

</style>