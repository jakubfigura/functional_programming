const express = require("express")
const app = express()

app.use(express.json())

const port = 8080

// 3.0 zwróci wartość binarną czy podana na wejściu liczba jest liczbą
// pierwszą; wykorzysta Promise

function isPrime(number){
    return new Promise((resolve, reject) => {
        if(number < 0){
            reject("Provide positive value")
            return
        }

        if(typeof number !== "number"){
            reject("Provide numerical values")
        }

        if(number < 2){
            resolve(false)
            return
        }


        for(let i = 2; i <= Math.sqrt(number); i++){
            if(number % i === 0){
                resolve(false)
                return
            }
        }
        resolve(true)
    })
}

//endpoint do punktu 1 
app.post('/isPrime', (req, res) => {
    const {number} = req.body
    console.log(number)
    isPrime(number)
    .then(result => {
        res.json({
            number : number,
            isNumberPrime: result
        })
    })
    .catch(error => {
        res.status(400).json({
            error: error, 
            number: number
        })
    })
})

//endpoint do punktu 2

app.post('/sortList', (req, res) => {
    const {data} = req.body

})

app.listen(port, ()=>{
    console.log("Server is running on port" + port)
})