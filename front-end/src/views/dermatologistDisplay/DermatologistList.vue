<template>
    <div>
        <v-card class="mx-auto" elevation="0">
            <v-app-bar dark color="dark cyan" prominent>
                <v-toolbar-title>Dermatologists</v-toolbar-title>
            </v-app-bar>
            <v-container style="height: 100%;">
                <v-text-field label="Search here..." v-model="searchQuery"></v-text-field>
                <v-row dense>
                    <v-col cols="12"
                        v-for="dermatologist in filteredDermatologists"
                        :key="dermatologist.email">
                        <v-card>
                            <div class="align-items-center">
                                <div>
                                    <v-card-text>
                                        <h3 class="display-1 text--primary">{{dermatologist.name}} {{dermatologist.surname}}</h3>
                                        <br />
                                        <div class="text--primary">
                                            <h4>Contact:</h4>
                                            <p>{{dermatologist.phoneNumber}} | {{dermatologist.email}}</p>
                                            <h4>Employed at:</h4>
                                            <p v-for="pharmacyName in dermatologist.pharmacyNames" :key="pharmacyName">
                                                {{pharmacyName}}
                                            </p>
                                            <h4>Rating: {{dermatologist.rating.toFixed(2)}}</h4>
                                        </div>
                                    </v-card-text>
                                    <v-card-actions v-if="context === 'PHARMA'">
                                        <v-btn @click="deleteEmployee(dermatologist.email)">
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
    name: "DermatologistList",
    data() {
        return {
            searchQuery: '',
            dermatologists: [],
            ratings: []
        }
    },
    props: ['context'],
    computed: {
        filteredDermatologists() {
            if (!this.searchQuery)
                return this.dermatologists
            return this.dermatologists.filter(this.searchCriteria(this.searchQuery.trim()))
        }
    },
    methods: {
        searchCriteria(query) {
            return function(dermatologist) {
                let fullName = dermatologist.name + ' ' + dermatologist.surname
                if (fullName.toUpperCase().includes(query.toUpperCase()))
                    return true
                if (dermatologist.email.toUpperCase().includes(query.toUpperCase()))
                    return true
                for (let pharma of dermatologist.pharmacyNames)
                    if (pharma.toUpperCase().includes(query.toUpperCase()))
                    return true
                return false
            }
        },
        deleteEmployee(email) {
            alert('Deleting ' + email)
            let path = 'http://localhost:9090/api/dermatologists/delete/employment/'
            path += this.$route.params.regNo + '/' + email
            axios
            .delete(path, { headers: authHeader() })
            .then(response => {
                alert('Successfully deleted the dermatologist')
                console.log(response)
                this.$router.go()
            })
            .catch(error => {
                alert(error.response.data)
            })
        }
    },
    mounted() {
        let path = 'http://localhost:9090/api/dermatologists/list/'
        let ratingsPath = 'http://localhost:9090/api/dermatologists/'
        if (this.context === 'PHARMA') {
            path += 'pharmacy/' + this.$route.params.regNo
            ratingsPath += `ratings/${this.$route.params.regNo}`
        }
        else {
            path += 'all'
            ratingsPath += 'all-ratings'
        }

        let dermatologistsRequest = axios.get(path, { headers: authHeader() })
        let ratingsRequest = axios.get(ratingsPath, { header: authHeader() })

        axios
            .all([dermatologistsRequest, ratingsRequest])
            .then(axios.spread((...responses) => {
                this.dermatologists = responses[0].data
                this.ratings = responses[1].data
                for (let dermatologist of this.dermatologists) {
                    for (let rating of this.ratings) {
                        if (rating.medicalWorkerEmail === dermatologist.email) {
                            dermatologist['rating'] = rating.rating
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

<style>

</style>