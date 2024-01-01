/**
 * Fetches data from the specified endpoint using a GET request and processes it using the provided handler.
 * @param {string} endpoint - The endpoint to fetch data from.
 * @param {function} handler - The callback function to handle the fetched data.
 */
function fetchData(endpoint, handler) {
    fetch(endpoint, {
        method: 'GET',
    })
        .then((response) => response.json())
        .then((data) => handler(data))
        .catch((error) => console.error('Error:', error));
}

/**
 * Gets the permutations for the given word and displays them.
 * @param {string} word - The input word for which permutations are generated.
 */
function allPermutations(word) {
    if (!word || word.trim() === '') {
        return;
    }
    fetchData(`api/permutations/${word}`, displayPermutations);
 }

/**
 * Gets valid permutations for the given word and displays them along with meanings and pronunciations.
 * @param {string} word - The input word for which valid permutations are found.
 */
function validPermutations(word) {
    if (!word || word.trim() === '') {
        return;
    }
    var resultContainer = document.getElementById('result-container');
    resultContainer.innerHTML = 'Please wait...';
    fetchData(`api/valid/${word}`, displayValidPermutations);
 }

/**
 * Displays the valid permutations with their meanings and pronunciations in the result container.
 * @param {Array} data - The array of objects containing words and their meanings.
 */
function createBlockDiv() {
    var blockDiv = document.createElement('div');
    blockDiv.classList.add('block');
    return blockDiv;
}

/**
 * Creates and returns an H2 heading element for a word object.
 * @param {Object} wordObject - The object containing information about the word.
 * @returns {HTMLHeadingElement} - The created H2 heading element.
 */
function createBlockHeading(wordObject) {
    var blockHeading = document.createElement('h2');
    blockHeading.textContent = `${wordObject.word}`;
    return blockHeading;
}

/**
 * Creates and returns an unordered list element for the meanings of a word object.
 * @param {Object} wordObject - The object containing information about the word and its meanings.
 * @returns {HTMLUListElement} - The created unordered list element.
 */
function createMeaningsList(wordObject) {
    var meaningsList = document.createElement('ul');

    wordObject.meanings.forEach((meaning) => {
        var listItem = createListItemValid(meaning);
        meaningsList.appendChild(listItem);
    });

    return meaningsList;
}

/**
 * Creates and returns a list item element for a meaning object.
 * @param {Object} meaning - The object containing information about the meaning of a word.
 * @returns {HTMLLIElement} - The created list item element.
 */
function createListItemValid(meaning) {
    var listItem = document.createElement('li');

    var defSpan = createDefSpan(meaning);
    var fullTextContent = `${meaning.partOfSpeech}: ${defSpan.outerHTML} (${meaning.pronunciation})`;
    listItem.innerHTML = fullTextContent;

    return listItem;
}

/**
 * Creates and returns a list item element for a result.
 * @param {string} result - The result to be displayed in the list item.
 * @returns {HTMLLIElement} - The created list item element.
 */
function createListItem(result) {
    var listItem = document.createElement('li');
    listItem.textContent = result;
    return listItem;
 }

 /**
 * Creates and returns a span element for the definition of a meaning object.
 * @param {Object} meaning - The object containing information about the meaning of a word.
 * @returns {HTMLSpanElement} - The created span element for the definition.
 */
function createDefSpan(meaning) {
    var defSpan = document.createElement('span');
    defSpan.classList.add('def');
    defSpan.textContent = meaning.definition;
    return defSpan;
}

/**
 * Displays the generated permutations in the result container.
 * @param {Array} data - The array of permutations to be displayed.
 */
function displayPermutations(data) {
    var resultContainer = document.getElementById('result-container');
    resultContainer.innerHTML = '';

    var blockDiv = createBlockDiv();
    blockDiv.classList.add('permutations');
    resultContainer.appendChild(blockDiv);

    data.forEach((result) => {
        var listItem = createListItem(result);
        blockDiv.appendChild(listItem);
    });
}

/**
 * Displays the valid permutations and their meanings in the result container.
 * @param {Array} data - The array of objects containing words and their meanings.
 */
function displayValidPermutations(data) {
    var resultContainer = document.getElementById('result-container');
    resultContainer.innerHTML = '';

    if (data.length === 0) {
        resultContainer.innerHTML = 'No valid permutations found';
        return;
    }

    data.forEach((wordObject) => {
        var blockDiv = createBlockDiv();
        resultContainer.appendChild(blockDiv);

        var blockHeading = createBlockHeading(wordObject);
        blockDiv.appendChild(blockHeading);

        var meaningsList = createMeaningsList(wordObject);
        blockDiv.appendChild(meaningsList);
    });
}

document.getElementById('generatePermutations').addEventListener('click', function () {
    var word = document.getElementById('wordInput').value;
    allPermutations(word);
});

document.getElementById('findValidPermutations').addEventListener('click', function () {
    var word = document.getElementById('wordInput').value;
    validPermutations(word);
});  