/**
 * @license
 * Copyright 2013 Google LLC
 * SPDX-License-Identifier: Apache-2.0
 */

/**
 * @fileoverview Blocks for Bird game.
 * @author q.neutron@gmail.com (Quynh Neutron)
 */
'use strict';

goog.provide('Bird.Blocks');

goog.require('Blockly');
goog.require('Blockly.Constants.Logic');
goog.require('Blockly.Extensions');
goog.require('Blockly.FieldAngle');
goog.require('Blockly.FieldDropdown');
goog.require('Blockly.FieldNumber');
goog.require('Blockly.JavaScript');
goog.require('Blockly.JavaScript.logic');
goog.require('BlocklyGames');


/**
 * Construct custom bird block types.  Called on page load.
 */
Bird.Blocks.init = function() {
  /**
   * Common HSV hue for all variable blocks.
   */
  const VARIABLES_HUE = 330;

  /**
   * HSV hue for movement block.
   */
  const MOVEMENT_HUE = 290;

  /**
   * Atal honetan, JSON objektuez osatutako lista bat sortzen da. Objektu hauetako bakoitzean bloke bat osatzeko beharrezko datuak biltzen dira, hainbat
   * aldagai erabiliz. Aldagaien esanahiak hauek dira:
   * type: blokearen identifikatzailea.
   * message0: blokean idatzita agertuko den textua.
   * args0: blokeak aldagaiak jasotzen dituenean soilik erabiltzen da. Jasotako aldagaien datuak biltzen dituzten JSON objektuen lista da. Objektuen egitura hau da:
   *         type: aldagaiaren identifikatzailea.
   *         name: aldagaiaren izena.
   *         angle: hautazkoa da. Aldagaiak adierazten duen angelua adierazten du.
   *         value: hautazkoa da. Aldagaiaren balioa adierazten du.
   *         options: hautazkoa da. Erabiltzaileari eskeintzen zaizkion aukera desberdinak. Bikotetan adierazten dira, lehenengoa blokean idatzita agertzen den ikurra eta bigarrena ikurrari lotutako balioa izanik.
   *         check: hautazkoa da. Jasotako balioari ezartzen zaion baldintza. Adibidez 'check:number' kasuetan, erabiltzaileak sartutako balioa zenbaki bat dela ziurtatu behar dela esan nahi du.
   * inputsInLine: args0 existitzen denean soilik erabiltzen den hautazko atala. 'true' balioa ezartzen zaio jaso behar diren aldagai guztiak marra bakarrean adierazteko, bestela aldagai bakoitza marra bakarrean agertzen da. 
   * output: blokeak balioren bat itzuli behar badu soilik erabiltzen da. Bueltatzen duen balio mota adierazten du.
   * previousStatement: blokearen aurretik beste blokeak ahal direnean konektatu erabiltzen da. Hasieran 'null' balio du, ez duelako aurretik blokerik konektatuta.
   * nextStatement: blokearen atzetik beste blokeak ahal direnean konektatu erabiltzen da. Hasieran 'null' balio du, ez duelako atzetik blokerik konektatuta.
   * colour: blokearen kolorearen HSV balioa.
   * tooltip: erabiltzaileari blokearen funtzionamendua azaltzeko erabiltzen den textu lagungarria.
   * helpURL: blokearen funtzionamenduaren inguruko informazio gehigarria duen esteka. Jokoan blokearen gainean arratoiaren eskuineko botoia sakatuta agertzen da.
   * extensions: blokeari balio gehigarri bat duen beste bloke bat ahal zaionean konektatu erabiltzen da. Atal honen balioak onartzen diren balio gehigarriak adierazten ditu.
   *
   */
  Blockly.defineBlocksWithJsonArray([
    // Block for no worm condition.
    {
      "type": "bird_noWorm",
      "message0": BlocklyGames.getMsg('Bird.noWorm', false),
      "output": "Boolean",
      "colour": VARIABLES_HUE,
      "tooltip": BlocklyGames.getMsg('Bird.noWormTooltip', false),
    },

    // Block for moving bird in a direction.
    {
      "type": "bird_heading",
      "message0": BlocklyGames.getMsg('Bird.heading', false) + "%1",
      "args0": [
        {
          "type": "field_angle",
          "name": "ANGLE",
          "angle": 90,
        }
      ],
      "previousStatement": null,
      "nextStatement": null,
      "colour": MOVEMENT_HUE,
      "tooltip": BlocklyGames.getMsg('Bird.headingTooltip', false),
    },

    // Block for getting bird's x or y position.
    {
      "type": "bird_position",
      "message0": "%1",
      "args0": [
        {
          "type": "field_dropdown",
          "name": "XY",
          "options": [["x", "X"], ["y", "Y"]],
        }
      ],
      "output": "Number",
      "colour": VARIABLES_HUE,
      "tooltip": BlocklyGames.getMsg('Bird.positionTooltip', false),
    },

    // Block for comparing bird's x or y position with a number.
    {
      "type": "bird_compare",
      "message0": `%1%2%3`,
      "args0": [
        {
          "type": "input_value",
          "name": "A",
          "check": "Number",
        },
        {
          "type": "field_dropdown",
          "name": "OP",
          "options": [['\u200F<', 'LT'], ['\u200F>', 'GT']],
        },
        {
          "type": "input_value",
          "name": "B",
          "check": "Number",
        },
      ],
      "inputsInline": true,
      "output": "Boolean",
      "colour": "%{BKY_LOGIC_HUE}",
      "helpUrl": "%{BKY_LOGIC_COMPARE_HELPURL}",
      "extensions": ["bird_compare_tooltip"],
    },

    // Block for logical operator 'and'.
    {
      "type": "bird_and",
      "message0": "%1%{BKY_LOGIC_OPERATION_AND}%2",
      "args0": [
        {
          "type": "input_value",
          "name": "A",
          "check": "Boolean",
        },
        {
          "type": "input_value",
          "name": "B",
          "check": "Boolean",
        },
      ],
      "inputsInline": true,
      "output": "Boolean",
      "colour": "%{BKY_LOGIC_HUE}",
      "tooltip": "%{BKY_LOGIC_OPERATION_TOOLTIP_AND}",
      "helpUrl": "%{BKY_LOGIC_OPERATION_HELPURL}",
    },

    // Block for 'if/else'.
    {
      "type": "bird_ifElse",
      "message0": "%{BKY_CONTROLS_IF_MSG_IF}%1%{BKY_CONTROLS_IF_MSG_THEN}%2%{BKY_CONTROLS_IF_MSG_ELSE}%3",
      "args0": [
        {
          "type": "input_value",
          "name": "CONDITION",
          "check": "Boolean",
        },
        {
          "type": "input_statement",
          "name": "DO",
        },
        {
          "type": "input_statement",
          "name": "ELSE",
        },
      ],
      "colour": "%{BKY_LOGIC_HUE}",
      "tooltip": "%{BKY_CONTROLS_IF_TOOLTIP_2}",
      "helpUrl": "%{BKY_CONTROLS_IF_HELPURL}",
    },

    // Block for numeric value.
    {
      "type": "math_number",
      "message0": "%1",
      "args0": [{
        "type": "field_number",
        "name": "NUM",
        "value": 0,
      }],
      "output": "Number",
      "helpUrl": "%{BKY_MATH_NUMBER_HELPURL}",
      "colour": "%{BKY_MATH_HUE}",
      "tooltip": "%{BKY_MATH_NUMBER_TOOLTIP}",
      "extensions": ["parent_tooltip_when_inline"],
    },
  ]);

  Blockly.Extensions.register('bird_compare_tooltip',
      Blockly.Extensions.buildTooltipForDropdown('OP',
          {
            'LT': '%{BKY_LOGIC_COMPARE_TOOLTIP_LT}',
            'GT': '%{BKY_LOGIC_COMPARE_TOOLTIP_GT}',
          }));
};

/**
 * Funtzio hauek JSON objektuetatik blokeen informazioa jasotzen dute eta, beharrezko kasuetan, datuekin eragiketak egiten dituzte.
 * @args JavaScript kode bihurtu behar den blokearen JSON objektua.
 * @returns bi elementuko lista bat. Lehenengo elementua blokean erabiltzen diren aldagaiekin osatutako String bat (code) da (' ' erabiliz banatua),
 *          eta bigarrena egin nahi den eragiketa motarekin lotutako Blockly-ko aldagai bat (order).
 * Blockly-ko aldagaiak honako hauek dira:
 *           Blockly.JavaScript.ORDER_FUNCTION_CALL: funtzio gehigarri bati dei egin behar denean erabiltzen dela ematen du. Hau da, blokearen funtzionamendua
 *                                                   ez da Blockly-k (ez Blockly Games) eskeinitako aukerekin bateragarria eta, ondorioz, JavaScript-a
 *                                                   sortzeko funtzio gehigarria sortu behar da.
 *
 *           Blockly.JavaScript.ORDER_RELATIONAL: blokeak bi aldagai konparatu behar dituenean erabiltzen da. Honek, 'code' atalean zehaztutako konparaketa erabiltzen du.
 *                                                Konparaketa hau egokia izateko "aldagai1" + ' ' + "konparatzailea" + ' ' + "aldagai2" formatua jarraitu behar da 'code' aldagaiean.
 *
 *           Blockly.JavaScript.ORDER_LOGICAL_AND: bloketik lortutako kodean 'AND' logikoa erabili behar denean erabiltzen da. Gainera, hau erabili ezkero, 'code' aldagaiaren formatua
 *                                                 honako hau izan behar da: "aldagai1" + ' ' + "&&" + ' ' + "aldagai2"
 *
 *           Blockly.JavaScript.ORDER_ATOMIC: bloketik lortutako aldagai bat gorde behar denean erabiltzen da (gero beste blokeren batekin lotzean erabiltzeko).
 *           Blockly.JavaScript.ORDER_UNARY_NEGATION: bloketik lortutako zenbaki bati zeinua aldatu nahi zaionean erabiltzen da. Zeinua aldatu ondoren, aurreko kasuan bezala,
 *                                                    aldagai berria gordetzen du.
*/

Blockly.JavaScript['bird_noWorm'] = function(block) {
  // Generate JavaScript for no worm condition.
  return ['noWorm()', Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.JavaScript['bird_heading'] = function(block) {
  // Generate JavaScript for moving bird in a direction.
  const dir = Number(block.getFieldValue('ANGLE'));
  return `heading(${dir}, 'block_id_${block.id}');\n`;
};

Blockly.JavaScript['bird_position'] = function(block) {
  // Generate JavaScript for getting bird's x or y position.
  const code = `get${block.getFieldValue('XY').charAt(0)}()`;
  return [code, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.JavaScript['bird_compare'] = function(block) {
  // Generate JavaScript for comparing bird's x or y position with a number.
  const operator = (block.getFieldValue('OP') === 'LT') ? '<' : '>';
  const order = Blockly.JavaScript.ORDER_RELATIONAL;
  const argument0 = Blockly.JavaScript.valueToCode(block, 'A', order) || '0';
  const argument1 = Blockly.JavaScript.valueToCode(block, 'B', order) || '0';
  const code = argument0 + ' ' + operator + ' ' + argument1;
  return [code, order];
};

Blockly.JavaScript['bird_and'] = function(block) {
  // Generate JavaScript for logical operator 'and'.
  const order = Blockly.JavaScript.ORDER_LOGICAL_AND;
  let argument0 = Blockly.JavaScript.valueToCode(block, 'A', order);
  let argument1 = Blockly.JavaScript.valueToCode(block, 'B', order);
  if (!argument0 && !argument1) {
    // If there are no arguments, then the return value is false.
    argument0 = 'false';
    argument1 = 'false';
  } else {
    // Single missing arguments have no effect on the return value.
    if (!argument0) {
      argument0 = 'true';
    }
    if (!argument1) {
      argument1 = 'true';
    }
  }
  const code = argument0 + ' && ' + argument1;
  return [code, order];
};

Blockly.JavaScript['bird_ifElse'] = function(block) {
  // Generate JavaScript for 'if/else' conditional.
  const argument = Blockly.JavaScript.valueToCode(block, 'CONDITION',
                   Blockly.JavaScript.ORDER_NONE) || 'false';
  const branch0 = Blockly.JavaScript.statementToCode(block, 'DO');
  const branch1 = Blockly.JavaScript.statementToCode(block, 'ELSE');
  return `if (${argument}) {\n${branch0}} else {\n${branch1}}\n`;
};

// Backup the initialization function on the stock 'if' block.
Blockly.Blocks['controls_if'].oldInit = Blockly.Blocks['controls_if'].init;

  /**
   * Modify the stock 'if' block to be a singleton.
   * @this {Blockly.Block}
   */
Blockly.Blocks['controls_if'].init = function() {
  this.oldInit();
  this.setPreviousStatement(false);
  this.setNextStatement(false);
};

Blockly.JavaScript['math_number'] = function(block) {
  // Numeric value.
  const code = Number(block.getFieldValue('NUM'));
  const order = code >= 0 ? Blockly.JavaScript.ORDER_ATOMIC :
      Blockly.JavaScript.ORDER_UNARY_NEGATION;
  return [code, order];
};
