const report = require('multiple-cucumber-html-reporter');

report.generate({
    jsonDir: './target/cucumber/',
    reportPath: './test output/CucumberReport/',
    displayDuration: true,
    displayReportTime: true,
    hideMetadata: true,
});