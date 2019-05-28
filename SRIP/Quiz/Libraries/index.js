var UserAnswers = [];
var RandomNumbers = [];
var ArrayEmpty = 1;
var CorrectCount = 0;
var TotalContainer = 0;


function generateQuestionContainer(QID, AID) {
    var containerDiv = document.getElementById("quizBody");
    var parentDiv = document.createElement("div");
    var QuestionDiv = document.createElement("div");
    QuestionDiv.id = QID;       //Q1
    QuestionDiv.className = "question";
    parentDiv.appendChild(QuestionDiv);

    for (var i = 0; i < 3; i++) {
        var OptionDiv = document.createElement("div");
        OptionDiv.className = "answer";

        var input = document.createElement("INPUT");
        input.setAttribute("type", "radio");
        input.id = AID.concat((i + 1).toString());    //A11
        input.name = QID;     //Q1

        var span = document.createElement("SPAN");
        span.id = QID.concat((i + 1).toString());     //Q11

        OptionDiv.appendChild(input);
        OptionDiv.appendChild(span);
        parentDiv.appendChild(OptionDiv);
    }

    containerDiv.appendChild(parentDiv);
    var br = document.createElement("BR");
    var hr = document.createElement("HR");
    containerDiv.insertBefore(br, parentDiv);
    containerDiv.insertBefore(hr, parentDiv);

}
function generateResultContainer(RID) {
    var containerDiv = document.getElementById("displayResult");
    var parentDiv = document.createElement("div");
    var QuestionDiv = document.createElement("div");
    QuestionDiv.id = RID;       //R1
    QuestionDiv.className = "question";
    parentDiv.appendChild(QuestionDiv);

    for (var i = 0; i < 2; i++) {
        var AnswerDiv = document.createElement("div");
        AnswerDiv.className = "answer";

        var span = document.createElement("SPAN");
        span.id = RID.concat((i + 1).toString());     //R11

        AnswerDiv.appendChild(span);
        parentDiv.appendChild(AnswerDiv);
    }

    containerDiv.appendChild(parentDiv);
    var br = document.createElement("BR");
    var hr = document.createElement("HR");
    containerDiv.insertBefore(br, parentDiv);
    containerDiv.insertBefore(hr, parentDiv);
}
function putContainers() {
    TotalContainer = Math.floor((Math.random() * 5) + 3);
    var QId = "Q";
    var AId = "A";
    var RId = "R";
    for (var i = 0; i < TotalContainer; i++) {
        var Qstring = QId.concat((i + 1).toString());
        var Astring = AId.concat((i + 1).toString());
        var Rstring = RId.concat((i + 1).toString());
        generateQuestionContainer(Qstring, Astring);
        generateResultContainer(Rstring);
    }
}

function putResult() {
    var RID = "R";
    var QID="Q";
    for (var i = 0; i < TotalContainer; i++) {
        var TempQID= QID.concat((i + 1).toString());    //Q1
        var TempRID = RID.concat((i + 1).toString());   //R1
        var AnsID = TempRID.concat("1");   //R11
        var UserAnsID = TempRID.concat("2");    //R12
        var ResultStatus = "Correct Answer : ";
        document.getElementById(TempRID).innerHTML = jsonData[RandomNumbers[i]].q;
        document.getElementById(AnsID).innerHTML = ResultStatus + jsonData[RandomNumbers[i]].answer;
        if (UserAnswers[i] === -1) {
            document.getElementById(UserAnsID).innerHTML = "Question Skipped";
            document.getElementById(UserAnsID).style.color = "brown";
        }
        else {
            var t1;
            var t2;
            if (UserAnswers[i] === 1) {
                t1 = document.getElementById(TempQID.concat("1")).innerHTML;
                t2 = jsonData[RandomNumbers[i]].answer;
            }
            else if (UserAnswers[i] === 2) {
                t1 = document.getElementById(TempQID.concat("2")).innerHTML;
                t2 = jsonData[RandomNumbers[i]].answer;
            }
            else if (UserAnswers[i] === 3) {
                t1 = document.getElementById(TempQID.concat("3")).innerHTML;
                t2 = jsonData[RandomNumbers[i]].answer;
            }
            if (t1 !== t2) {
                ResultStatus = "Your Answer is Incorrect : ";
                document.getElementById(UserAnsID).style.color = "red";
            } else {
                ResultStatus = "Your Answer is Correct : ";
                document.getElementById(UserAnsID).style.color = "green";
            }
            document.getElementById(UserAnsID).innerHTML = ResultStatus + t1;
        }
    }
}
function removeChilds() {
    var qDiv = document.getElementById("quizBody");
    while (qDiv.hasChildNodes()) {
        qDiv.removeChild(qDiv.firstChild);
    }

    var rDiv = document.getElementById("displayResult");
    while (rDiv.hasChildNodes()) {
        rDiv.removeChild(rDiv.firstChild);
    }
}
function startQuiz() {
    document.getElementById("TaskTitle").innerHTML = "Quiz for MIPS Parser";
    document.getElementById("result").style.display = "none";
    document.getElementById("displayResult").style.display = "none";
    document.getElementById("startBtn").style.visibility = "hidden";
    document.getElementById("submitBtn").style.visibility = "visible";
    removeChilds();
    putContainers();
    putQuestion();
    document.getElementById("quizBody").style.display = "block";
}

function submitQuiz() {
    document.getElementById("submitBtn").style.visibility = "hidden";
    document.getElementById("startBtn").style.visibility = "visible";
    submitAnswers();
    document.getElementById("quizBody").style.display = "none";
    document.getElementById("result").innerHTML = (CorrectCount).toString() + " out of " + TotalContainer;
    putResult();
    document.getElementById("TaskTitle").innerHTML = "Quiz Results";
    document.getElementById("result").style.display = "block";
    document.getElementById("displayResult").style.display = "block";
    CorrectCount = 0;
    ArrayEmpty = 1;
    UserAnswers = [];
    RandomNumbers = [];
}

function generateRandomIndex() {
    var x = Math.floor((Math.random() * TotalContainer) + 0);
    var temp = RandomNumbers.indexOf(x);
    while (temp != -1 && ArrayEmpty === 0) {
        x = Math.floor((Math.random() * TotalContainer) + 0);
        temp = RandomNumbers.indexOf(x);
    }
    RandomNumbers.push(x);
    ArrayEmpty = 0;
    return x;
}

function getContent(TempQID) {
    var RandomIndex = generateRandomIndex();

    var tempStack = [];
    for (var i = 0; i < 3; i++) {

        var x = Math.floor((Math.random() * 3)+1);
        var temp = tempStack.indexOf(x);
        while (temp != -1) {
            x = Math.floor((Math.random() * 3) + 1);
            temp = tempStack.indexOf(x);
        }
        tempStack.push(x);
    }

    document.getElementById(TempQID).innerHTML = jsonData[RandomIndex].q;
    document.getElementById(TempQID.concat((tempStack[0]).toString())).innerHTML = jsonData[RandomIndex].opt1;
    document.getElementById(TempQID.concat((tempStack[1]).toString())).innerHTML = jsonData[RandomIndex].opt2;
    document.getElementById(TempQID.concat((tempStack[2]).toString())).innerHTML = jsonData[RandomIndex].opt3;
}

function putQuestion() {
    var QID = "Q";
    for (var i = 0; i < TotalContainer; i++) {
        var TempQID = QID.concat((i + 1).toString());
        getContent(TempQID);
    }
}

function checkAnswer(AnsId, JsonId, QId) {
    var userAns = -1;
    if (document.getElementById(AnsId.concat("1")).checked) {
        userAns = 1;
        if (document.getElementById(QId.concat("1")).innerHTML == jsonData[JsonId].answer) {
            CorrectCount = CorrectCount + 1;
        }
    }
    if (document.getElementById(AnsId.concat("2")).checked) {
        userAns = 2;
        if (document.getElementById(QId.concat("2")).innerHTML == jsonData[JsonId].answer) {
            CorrectCount = CorrectCount + 1;
        }
    }
    if (document.getElementById(AnsId.concat("3")).checked) {
        userAns = 3;
        if (document.getElementById(QId.concat("3")).innerHTML == jsonData[JsonId].answer) {
            CorrectCount = CorrectCount + 1;
        }
    }
    return userAns;
}

function submitAnswers() {
    var AID = "A";
    var QID = "Q";
    for (var i = 0; i < TotalContainer; i++) {
        var TempAID = AID.concat((i + 1).toString());
        var JsonId = RandomNumbers[i];    //contains the questions index
        var TempQID = QID.concat((i + 1).toString());
        var userAns = checkAnswer(TempAID, JsonId, TempQID);
        UserAnswers.push(userAns);
    }
}
