<template>
    <div>
        <v-card class="mx-auto" elevation="0">
            <v-app-bar dark color="dark cyan" prominent>
                <v-toolbar-title>Pharmacists</v-toolbar-title>
            </v-app-bar>
            <v-container style="height: 100%;">
                <v-text-field label="Search here..." v-model="searchQuery"></v-text-field>
                <v-row dense>
                    <v-col cols="12"
                        v-for="pharmacist in filteredPharmacists"
                        :key="pharmacist.email">
                        <v-card>
                            <div class="align-items-center">
                                <div>
                                    <v-card-text>
                                        <h3 class="display-1 text--primary">{{pharmacist.name}} {{pharmacist.surname}}</h3>
                                        <br />
                                        <div class="text--primary">
                                            <h4>Contact:</h4>
                                            <p>{{pharmacist.phoneNumber}} | {{pharmacist.email}}</p>
                                            <h4>Employed at:</h4>
                                            <p>{{pharmacist.pharmacyName}}</p>
                                            <h4>Rating: {{pharmacist.rating.toFixed(2)}}</h4>
                                        </div>
                                    </v-card-text>
                                    <v-card-actions v-if="context === 'PHARMA'">
                                        <v-btn @click="deleteEmployee(pharmacist.email)">
                                            Delete
                                        </v-btn>
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
import authHeader from '../../services/auth-header'
export default {
    name: "PharmacistList",
    data() {
        return {
            searchQuery: '',
            pharmacists: [],
            ratings: []
        }
    },
    props: ['context'],
    computed: {
        filteredPharmacists() {
            if (!this.searchQuery)
                return this.pharmacists
            return this.pharmacists.filter(this.searchCriteria(this.searchQuery.trim()))
        }
    },
    methods: {
        searchCriteria(query) {
            return function(pharmacist) {
                let fullName = pharmacist.name + ' ' + pharmacist.surname
                if (fullName.toUpperCase().includes(query.toUpperCase()))
                    return true
                if (pharmacist.email.toUpperCase().includes(query.toUpperCase()))
                    return true
                if (pharmacist.pharmacyName.toUpperCase().includes(query.toUpperCase()))
                    return true
                return false
            }
        },
        deleteEmployee(email) {
            alert('Deleting ' + email)
            let path = 'http://localhost:9090/api/pharmacist/delete/employment/'
            path += email
            axios
            .delete(path, { headers: authHeader() })
            .then(response => {
                alert('Successfully deleted the pharmacist')
                console.log(response)
                this.$router.go()
            })
            .catch(error => {
                console.log(JSON.stringify(error))
                alert(error.message)
            })
        }
    },
    mounted() {
        let path = 'http://localhost:9090/api/pharmacist/list/'
        let ratingsPath = 'http://localhost:9090/api/pharmacist/'
        if (this.context === 'PHARMA') {
            path += 'pharmacy/' + this.$route.params.regNo
            ratingsPath += `ratings/${this.$route.params.regNo}`
        }
        else {
            path += 'all'
            ratingsPath += 'all-ratings'
        }

        let pharmacistsRequest = axios.get(path)
        let ratingsRequest = axios.get(ratingsPath)

        axios
            .all([pharmacistsRequest, ratingsRequest])
            .then(axios.spread((...responses) => {
                this.pharmacists = responses[0].data
                this.ratings = responses[1].data
                for (let pharmacist of this.pharmacists) {
                    for (let rating of this.ratings) {
                        if (rating.medicalWorkerEmail === pharmacist.email) {
                            pharmacist['rating'] = rating.rating
                            break
                        }
                    }
                }
            }))
            .catch(errors => {
                for (let error of errors)
                    console.log(error)
                alert('PROPO')
            })
    }
}
</script>
