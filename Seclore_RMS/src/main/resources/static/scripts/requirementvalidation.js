/**
 * 
 */
const startDate = document.getElementById("startDate")
const endDate = document.getElementById("endDate")
startDate.min = new Date().toISOString().split("T")[0];
endDate.min = new Date().toISOString().split("T")[0];

