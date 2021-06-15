<template>
    <div>
        <!-- Na ovu foru: https://vuetifyjs.com/en/components/cards/#grids -->
        <pharmacy-admin-home>
        </pharmacy-admin-home>
        <v-card class="mx-auto" elevation="0">
            <v-app-bar dark color="dark cyan" prominent>
                <v-toolbar-title>Medicine list</v-toolbar-title>
            </v-app-bar>
            <v-container>
                <v-text-field label="Search here..." v-model="searchQuery"></v-text-field>
                <v-row>
                    <v-col cols="12"
                        v-for="(med, index) in filteredMedicine"
                        :key="index">
                        <v-card>
                            <div class="d-flex flex-no-wrap justify-space-between">
                                <div>
                                    <v-card-title
                                    class="text-h5"
                                    v-text="med.code + ' | ' + med.name"></v-card-title>

                                    <v-card-subtitle class="text-h6" v-text="'Proizvodjac: ' + med.manufacturer + ', Sastav: ' + med.composition + ', Forma: ' + med.drugForm"></v-card-subtitle>

                                    <v-card-actions>
                                        <v-btn @click="viewPricing(med.code)">View pricing</v-btn>
                                        <v-btn outlined rounded small @click="createDiscount(med.code)">Create discount</v-btn>
                                        <v-btn outlined rounded small @click="deleteMed(med.code)">Delete medicine</v-btn>
                                    </v-card-actions>
                                </div>
                            </div>
                        </v-card>
                    </v-col>
                </v-row>
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
    name: 'MedicineList',
    data () {
        return {
            medicine: [],
            searchQuery: '',
        }
    },
    computed: {
        filteredMedicine () {
            if (!this.searchQuery.trim())
                return this.medicine
            return this.medicine.filter(this.searchCriteria(this.searchQuery.trim()))
        }
    },
    mounted () {
        axios
        .get(
            "http://localhost:9090/api/medicine/pharmacy/" + this.$route.params.regNo,
            { headers: authHeader() }
        )
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
        },
        deleteMed(code) {
            axios
            .delete(
                'http://localhost:9090/api/medicine/remove/pricing/' + this.$route.params.regNo + '/' + code, 
                { headers: authHeader() }
            )
            .then(response => {
                alert(response.data)
                this.$router.go()
            })
            .catch(response => {
                alert('Ups! Desila se greška na serveru!')
                console.log(response.data)
            })
        },
        createDiscount(code) {
            this.$router.push('/discount/' + this.$route.params.regNo + '/' + code)
        },
        searchCriteria(query) {
            return function(medicine) {
                if (medicine.code.toUpperCase().includes(query.toUpperCase()))
                    return true
                if (medicine.name.toUpperCase().includes(query.toUpperCase()))
                    return true
                if (medicine.manufacturer.toUpperCase().includes(query.toUpperCase()))
                    return true
                if (medicine.drugForm.toUpperCase().includes(query.toUpperCase()))
                    return true
                return false
            }
        }
    }
}
</script>

<style>

</style>