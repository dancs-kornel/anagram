document.getElementById('generatePermutations').addEventListener('click', function () {
    var word = document.getElementById('wordInput').value;
    fetch(`api/permutations/${word}`, {
        method: 'GET',
    })
        .then((response) => response.json())
        .then((data) => console.log(data))
        .catch((error) => console.error('Error:', error));
});


document.getElementById('findValidPermutations').addEventListener('click', function () {
    var word = document.getElementById('wordInput').value;
    fetch(`api/valid/${word}`, {
        method: 'GET',
    })
        .then((response) => response.json())
        .then((data) => console.log(data))
        .catch((error) => console.error('Error:', error));
});