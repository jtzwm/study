define(['views/resultView'], function (Utils) {
  "use strict";

  var threshold = 0.5;
  var score = Math.random();
  var level = score > threshold ? '积极进取型' : '稳健型';

  function init() {
    isPass();
    drawRound(score);
  }

  function isPass() {
    if (score > threshold) {
      $$('.canvas .redo').hide();
      $$('.canvas p').text('恭喜，您为激进型投资者，非常适合投资类产品');
      Utils.render();
    } else {
      mainView.hideToolbar();
    }
  }

  function drawRound(percentage) {
    var isGaugeInit = false,
      canvas = document.getElementsByTagName('canvas'),
      gaugeGroup = [],
      arcIncrements = [],
      cWidth = canvas[0].width,
      cHeight = canvas[0].height,
      baseColor = '#e1e1e1',
      coverColor = '#e45050';

    function drawCanvasRound(gauge, color, sAngle, eAngle) {
      gauge.clearRect(0, 0, cWidth, cHeight);

      gauge.beginPath();
      gauge.strokeStyle = color;
      gauge.lineWidth = 10;
      gauge.arc(cWidth / 2, cHeight / 2, 160, sAngle, eAngle, false);
      gauge.stroke();
    }

    function drawCanvasStaff(gauge, arcEndStaff) {
      drawCanvasRound(gauge, baseColor, 130 * Math.PI / 180, 50 * Math.PI / 180);
      // drawCanvasRound(gauge, coverColor, 0 - 90 * Math.PI / 180, arcEndStaff - 90 * Math.PI / 180);
      gauge.beginPath();
      gauge.strokeStyle = coverColor;
      gauge.lineWidth = 10;
      gauge.arc(cWidth / 2, cHeight / 2, 160, 130 * Math.PI / 180, arcEndStaff - 230 * Math.PI / 180, false);
      gauge.stroke();

      gauge.fillStyle = coverColor;
      gauge.font = '60px PT Sans';
      var text = level;
      var textWidth = gauge.measureText(text).width;
      gauge.fillText(text, cWidth / 2 - textWidth / 2, cHeight / 2 + 30);
      gauge.font = '26px PT Sans';
      gauge.fillStyle = '#ccc';

      return arcEndStaff;
    }

    function initCanvasStaff() {

      for (var i = 0, cl = canvas.length; i < cl; i++) {
        var gauge = canvas[i].getContext('2d');
        var radian = percentage * 280 * Math.PI / 180;
        gaugeGroup.push(gauge);
        arcIncrements.push(0);
      }

      var drawingStaff1 = setInterval(function () {
        arcIncrements[0] += Math.PI / 180;
        var end1 = drawCanvasStaff(gaugeGroup[0], arcIncrements[0]);
        if (end1 > radian) {
          clearInterval(drawingStaff1);
        }
      }, 10);

    }

    initCanvasStaff(percentage);
  }

  return {
    init: init
  };
});
