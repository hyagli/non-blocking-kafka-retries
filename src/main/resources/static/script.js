function fetchRecords() {
    setStatus("warning");
    fetch('/get-messages')
        .then((response) => response.json())
        .then((data) => {
            fillTable("tb1", data.consumer1);
            fillTable("tb2", data.consumer2);
            fillTable("tb3", data.consumer3);
            setStatus("success");
        });

    setTimeout(fetchRecords, 500);
}

function fillTable(tableBodyId, data) {
    let tableBody = document.getElementById(tableBodyId)
    tableBody.innerHTML = ""
    data.forEach(row => addRow(tableBody, row));
}

function addRow(tableBody, rowData) {
    let row = tableBody.insertRow();
    let cell1 = row.insertCell();
    let cell2 = row.insertCell();
    cell1.innerHTML = rowData.timestamp;
    cell2.innerHTML = rowData.message;
}

function setStatus(color) {
    document.getElementById("bg-status").classList.remove("text-bg-secondary");
    document.getElementById("bg-status").classList.remove("text-bg-warning");
    document.getElementById("bg-status").classList.add("text-bg-" + color);
}

fetchRecords();
