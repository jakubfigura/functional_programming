const express = require("express")
const app = express()
const { Worker } = require('worker_threads');
const path = require('path');

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

        if (number === 0 || number === 1){
            reject("Number is neither prime nor composite")
        }

        if(typeof number !== "number"){
            reject("Provide numerical values")
        }
        /*
        Bardziej funkcyjnie? 
        */
       const limit = Math.floor(Math.sqrt(number))
       const checkTheNumber = Array.from({length : limit - 1}, (_, i) => i + 2).some(i => number % i === 0)
       resolve(!checkTheNumber)

        // for(let i = 2; i <= Math.sqrt(number); i++){
        //     if(number % i === 0){
        //         resolve(false)
        //         return
        //     }
        // }
        // resolve(true)
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


// 3.5  zwróci posortowaną listę; wykorzysta Promise
function sortList(data){
    return new Promise((resolve, reject) => {
        if (!Array.isArray(data)){
            reject("Provide array!")
            return
        }
        resolve(data.sort())

    })
}

//endpoint do punktu 2
app.post('/sortList', (req, res) => {
    const {data} = req.body
    console.log(data)
    sortList(data)
        .then(result => {
            res.json({ 
                sortedList : result
            })
        })
        .catch(error => {
            res.status(400).json({
            error: error
        })
    })

})

const runWorker = (data) => {
  return new Promise((resolve, reject) => {
    const worker = new Worker(path.join(__dirname, 'worker.js'));

    worker.on('message', (message) => {
      if (message.success) {
        resolve(message.data);
      } else {
        reject(new Error(message.error));
      }
      worker.terminate();
    });

    worker.on('error', reject);

    worker.on('exit', (code) => {
      if (code !== 0) {
        reject(new Error(`Worker stopped with exit code ${code}`));
      }
    });

    worker.postMessage(data);
  });
};

//endpoint do punktu 3
/*
4.0  zwróci słownik (student, godziny nauki), która wykorzysta funkcją
mapreduce oraz groupBy dla słownika na wejściu
*/

app.post("/dictionary", async (req, res) => {
    const { students } = req.body;

    try {
        const result = await runWorker(students);
        res.json({
            studentHours: result
        });
    } catch (error) {
        res.status(400).json({
            error: error.message
        });
    }
})

app.listen(port, ()=>{
    console.log("Server is running on port" + port)
})