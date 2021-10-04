$(function () {
    // Everything below happens on page load

    // Retrieve calendar events
    $.ajax({
        url: "http://localhost:8080/api/events",
        success: function (data) {
            populateCalendar(data);
        },
        error: function (jqHXR, textStatus, errorThrown) {
            handleError(jqHXR.responseText);
        },
        dataType: "json"
    });

    let confirmRsvp = function (data) {
        setStatusMessage(data.status);
        hideRsvpForm();
    };

    let populateCalendar = function (data) {
        data.forEach(element => element.type = 'Event');

        $('#evoCalendar')
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

    let setStatusMessage = function (text) {
        $("#status-message").text(text);
    };

    let handleError = function (error) {
        setStatusMessage(error);
        console.log(error);
    };

    let showRsvpForm = function (activeEvent) {
        if (activeEvent) {
            $("#event-id").val(activeEvent.id);
            $("#rsvp-form").show();
        } else {
            hideRsvpForm();
        }
    };

    let hideRsvpForm = function () {
        $("#event-id").val(-1);
        $("#rsvp-form").hide();
    };

    hideRsvpForm();
});
