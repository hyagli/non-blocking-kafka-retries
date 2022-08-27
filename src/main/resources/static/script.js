function fetchRecords() {
    setStatus("warning")
    fetch('/get-messages')
        .then((response) => response.json())
        .then((data) => {
            if (Array.isArray(data) && data.length) {
                createTables(data)
                fillTables(data)
            }
            setStatus("success")
        })

    setTimeout(fetchRecords, 500)
}

function createTables(consumers) {
    $(".tblBox").not("#box0").remove()
    let last = $("#box0")
    last.children("tr").remove()
    let count = 1
    while (count < consumers.length) {
        let clone = last.clone().attr("id", "box" + count)
        last.after(clone)
        last = clone
        count++
    }
}

function fillTables(consumers) {
    for (let i = 0; i < consumers.length; i++) {
        fillTable("#box" + i, consumers[i].topic, consumers[i].messages)
    }
}

function fillTable(tableBoxId, topic, messages) {
    let box = $(tableBoxId)
    box.find("h5").text(topic)
    let tableBody = box.find("tbody")
    messages.forEach(message =>
        tableBody.append(
            $("<tr>").append(
                $("<td>").text(message.text)
            ).append(
                $("<td>").text(message.created)
            )
        )
    )
}

function setStatus(color) {
    document.getElementById("bg-status").classList.remove("text-bg-secondary")
    document.getElementById("bg-status").classList.remove("text-bg-warning")
    document.getElementById("bg-status").classList.add("text-bg-" + color)
}

$(function () {
    fetchRecords()
})

$("form.kafka").submit(function (e) {
    e.preventDefault()
    let form = $(this)
    $.ajax({
        type: "POST",
        url: '/send-message',
        data: form.serialize(),
        success: function (data) {
            console.log(data)
        }
    })
})
