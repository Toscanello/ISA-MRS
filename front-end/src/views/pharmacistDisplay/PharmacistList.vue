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
export default {
    name: "PharmacistList",
    data() {
        return {
            searchQuery: '',
            pharmacists: []
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
            .delete(path)
            .then(response => {
                alert('Successfully deleted the pharmacist')
                console.log(response)
                this.$router.go()
            })
            .catch(error => {
                alert(error.response.data)
            })
        }
    },
    mounted() {
        let path = 'http://localhost:9090/api/pharmacist/list/'
        if (this.context === 'PHARMA')
            path += 'pharmacy/' + this.$route.params.regNo
        else
            path += 'all'
        console.log(path)
        axios
        .get(path) //headers
        .then(response => {
            console.log('OK')
            this.pharmacists = response.data
        })
        .catch(response => {
            console.log('PROPO')
            console.log(response)
        })
    }
}
</script>
