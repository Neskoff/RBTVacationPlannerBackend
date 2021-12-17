const ctx = document.getElementById('myChart').getContext('2d');
const used = document.getElementById('usedVacationDates').value;
const unused = document.getElementById('unusedVacationDates').value;
const myChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ['Employees that didnt use vacation days', 'Employees that used vacation days'],
        datasets: [{
            label: 'Employees that used vacation days',
            data: [unused, used],
            backgroundColor: [
                '#d71e1e',
                '#262626',
            ],
            borderColor: [
                '#d71e1e',
                '#262626',
            ],
            hoverOffset: 4
        }]
    },
    options: {}
});