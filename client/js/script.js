document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#rsvpButton").addEventListener("click", rsvp);
    hideRsvpForm();
    loadEvents();
});


async function rsvp() {

}

async function loadEvents() {
    const url = "http://localhost:8080/api/events";

    // GET is the default HTTP method for fetch
    fetch(url)
        .then(response => {
            if (response.ok)
                return response.json();
            else
                return response.json()
                    .then(data => {throw new Error(data.message)});
        })
        .then(data => populateCalendar(data))
        .catch(err => handleError(err));
}

function confirmRsvp(data) {
    setStatusMessage(data.rsvpStatus);
    hideRsvpForm();
};

function populateCalendar(data) {
    // data.forEach(element => element.type = "Event");

    $("#evoCalendar")
        .evoCalendar({
            calendarEvents: data,
            theme: 'Midnight Blue'
        })
        .evoCalendar('selectYear', 2023)
        .evoCalendar('selectMonth', 10)
        .on('selectEvent', function (event, activeEvent) {
            showRsvpForm(activeEvent);
            setStatusMessage("");
        })
        .on('selectDate', function (newDate, oldDate) {
            hideRsvpForm();
            setStatusMessage("");
        });
};

function setStatusMessage(text) {
    document.querySelector("#statusMessage").innerText = text;
};

function handleError (error) {
    setStatusMessage(error);
    console.log(error);
};

function showRsvpForm (activeEvent) {
    if (activeEvent) {
        document.querySelector("#eventId").value = activeEvent.id;
        document.querySelector("div.form").style.display = "block";
    } else {
        hideRsvpForm();
    }
};

function hideRsvpForm () {
    document.querySelector("#eventId").value = -1;
    document.querySelector("div.form").style.display = "none";
};

