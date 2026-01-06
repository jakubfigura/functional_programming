const { parentPort } = require('worker_threads');

// Funkcja mapReduce - mapuje i redukuje dane
const mapReduce = (array, mapFn, reduceFn, initialValue) => {
  const mapped = array.map(mapFn);
  return mapped.reduce(reduceFn, initialValue);
};

// Funkcja przetwarzająca dane studentów
const processStudentHours = (students) => {
  // Używamy mapReduce do grupowania i sumowania godzin w jednym kroku
  // map: każdy student -> [name, hours]
  // reduce będzie sumować godziny dla każdego z imion
  return mapReduce(
    students,
    student => ({ name: student.name, hours: student.hours }),  
    (acc, { name, hours }) => {                                  
      return {
        ...acc,
        [name]: (acc[name] || 0) + hours
      };
    },
    {}                                                        
  );
};

parentPort.on('message', (students) => {
  try {
    const result = processStudentHours(students);
    parentPort.postMessage({ success: true, data: result });
  } catch (error) {
    parentPort.postMessage({ success: false, error: error.message });
  }
});
