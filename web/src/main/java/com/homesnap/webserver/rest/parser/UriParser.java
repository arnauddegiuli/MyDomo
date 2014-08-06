/* Generated By:JavaCC: Do not edit this line. UriParser.java */
package com.homesnap.webserver.rest.parser;

import com.homesnap.engine.controller.light.stateValue.LightStatusValue;
import com.homesnap.webserver.rest.MissingParameterRestOperation;
import com.homesnap.webserver.rest.RestOperationException;
import com.homesnap.webserver.rest.MyDomoRestAPI;
import com.homesnap.webserver.rest.UnsupportedRestOperation;
import java.io.ByteArrayInputStream;


public class UriParser implements MyDomoRestAPI, UriParserConstants {
/*
CONTROLLER
[/house/labels/labelId/where || /house/groups/groupId/where || /controllers/where]

Light:
/[on|off] || [?what=on||?what=off]

Automation:
/[up|down|stop] || [?what=up||?what=down||?what=stop]

heating:
/[heating_on||heating_off||...status]/dimension?value || [?what=heating_on||heating_off|| ...] => seule la premiere permet de régler les dimensions!

HOUSE SERVLET

/house																			GET/DELETE
/house/labels																	GET/DELETE
/house/labels/labelId || /house/labels/label?id=id								GET/PUT/POST/DELETE
/house/labels/labelId/where || /house/labels/labelId/controller?id=id			GET/PUT/POST/DELETE
/house/groups																	GET
/house/groups/groupId || /house/groups/group?id=id								GET
/house/groups/groupId/where || /house/groups/groupId/controller?id=id			GET


GET
/house
/house/labels
/house/labels/labelId || /house/labels/label?id=id
/house/labels/labelId/where || /house/labels/labelId/controller?id=id

/house/groups
/house/groups/groupId
/house/groups/groupId/where

POST
/house/labels/labelId (title=Titre)
/house/labels/labelId/where (who=1)


PUT
/house/labels/labelId?title=Titre
/house/labels/labelId/where?who=1

DELETE
/house
/house/labels
/house/labels/labelId || /house/labels/label?id=id
/house/labels/labelId/where || /house/labels/labelId/controller?id=id
*/

        private MyDomoRestAPI listener;

        public static void parse(String uri, MyDomoRestAPI listener) throws ParseException, UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation
        {
                UriParser parser = new UriParser(new ByteArrayInputStream(uri.getBytes()));
                parser.listener = listener;
                parser.parseOneLine();
        }

        @Override
        public void onHouse() throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                listener.onHouse();
        }

        @Override
        public void onLabelList() throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                listener.onLabelList();
        }

        @Override
        public void onLabel(String labelId) throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                listener.onLabel(labelId);
        }

        @Override
        public void onControllerByLabel(String labelId, String where) throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                listener.onControllerByLabel(labelId, where);
        }

        @Override
        public void onGroupList() throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                listener.onGroupList();
        }

        @Override
        public void onGroup(String groupId) throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                listener.onGroup(groupId);
        }

        @Override
        public void onControllerByGroup(String groupId, String where) throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                listener.onControllerByGroup(groupId, where);
        }

        @Override
        public void onController(String where) throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                listener.onController(where);
        }

        @Override
        public void onStatus(String name, String[] value) throws UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
                // Call by the servlet with parameters = no need to parse again
        }

  final private void parseOneLine() throws ParseException, UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
        Token labelId;
        Token groupId;
        Token where;
    if (jj_2_32(2)) {
      jj_consume_token(16);
      jj_consume_token(HOUSE);
      if (jj_2_30(2)) {
        if (jj_2_27(2)) {
          jj_consume_token(16);
          jj_consume_token(LABELS);
          if (jj_2_8(2)) {
            jj_consume_token(16);
            jj_consume_token(LABEL);
            jj_consume_token(17);
            labelId = jj_consume_token(VALUE);
                                                                    onLabel(labelId.toString());
          } else if (jj_2_9(2)) {
            jj_consume_token(16);
            labelId = jj_consume_token(VALUE);
            if (jj_2_5(2)) {
              jj_consume_token(16);
              where = jj_consume_token(VALUE);
              if (jj_2_1(2)) {
                parameter();
                                                                             {if (true) return;}
              } else if (jj_2_2(2)) {
                jj_consume_token(0);
                                                                       onControllerByLabel(labelId.toString(), where.toString());
              } else {
                jj_consume_token(-1);
                throw new ParseException();
              }
            } else if (jj_2_6(2)) {
              jj_consume_token(16);
              jj_consume_token(CONTROLLER);
              if (jj_2_3(2)) {
                jj_consume_token(17);
                where = jj_consume_token(VALUE);
                                                                                         onControllerByLabel(labelId.toString(), where.toString());
              } else if (jj_2_4(2)) {
                jj_consume_token(16);
                where = jj_consume_token(VALUE);
                                                                                                             onControllerByLabel(labelId.toString(), where.toString());
              } else {
                jj_consume_token(-1);
                throw new ParseException();
              }
            } else if (jj_2_7(2)) {
              jj_consume_token(0);
                                                       onLabel(labelId.toString());
            } else {
              jj_consume_token(-1);
              throw new ParseException();
            }
          } else if (jj_2_10(2)) {
            jj_consume_token(0);
                                 onLabelList();
          } else {
            jj_consume_token(-1);
            throw new ParseException();
          }
        } else if (jj_2_28(2)) {
          jj_consume_token(16);
          jj_consume_token(GROUPS);
          if (jj_2_18(2)) {
            jj_consume_token(16);
            jj_consume_token(GROUP);
            jj_consume_token(17);
            groupId = jj_consume_token(VALUE);
                                                                    onGroup(groupId.toString());
          } else if (jj_2_19(2)) {
            jj_consume_token(16);
            groupId = jj_consume_token(VALUE);
            if (jj_2_15(2)) {
              jj_consume_token(16);
              where = jj_consume_token(VALUE);
              if (jj_2_11(2)) {
                parameter();
                                                                             {if (true) return;}
              } else if (jj_2_12(2)) {
                jj_consume_token(0);
                                                                      onControllerByGroup(groupId.toString(), where.toString());
              } else {
                jj_consume_token(-1);
                throw new ParseException();
              }
            } else if (jj_2_16(2)) {
              jj_consume_token(16);
              jj_consume_token(CONTROLLER);
              if (jj_2_13(2)) {
                jj_consume_token(17);
                where = jj_consume_token(VALUE);
                                                                                         onControllerByGroup(groupId.toString(), where.toString());
              } else if (jj_2_14(2)) {
                jj_consume_token(16);
                where = jj_consume_token(VALUE);
                                                                                                             onControllerByGroup(groupId.toString(), where.toString());
              } else {
                jj_consume_token(-1);
                throw new ParseException();
              }
            } else if (jj_2_17(2)) {
              jj_consume_token(0);
                                                       onGroup(groupId.toString());
            } else {
              jj_consume_token(-1);
              throw new ParseException();
            }
          } else if (jj_2_20(2)) {
            jj_consume_token(0);
                                       onGroupList();
          } else {
            jj_consume_token(-1);
            throw new ParseException();
          }
        } else if (jj_2_29(2)) {
          jj_consume_token(16);
          jj_consume_token(CONTROLLERS);
          if (jj_2_25(2)) {
            jj_consume_token(16);
            where = jj_consume_token(VALUE);
            if (jj_2_21(2)) {
              parameter();
                                                             {if (true) return;}
            } else if (jj_2_22(2)) {
              jj_consume_token(0);
                                                       onController(where.toString());
            } else {
              jj_consume_token(-1);
              throw new ParseException();
            }
          } else if (jj_2_26(2)) {
            jj_consume_token(16);
            jj_consume_token(CONTROLLER);
            if (jj_2_23(2)) {
              jj_consume_token(17);
              where = jj_consume_token(VALUE);
                                                                         onController(where.toString()); /* TODO manage other parameter */
            } else if (jj_2_24(2)) {
              jj_consume_token(16);
              where = jj_consume_token(VALUE);
                                                                                             onController(where.toString()); /* TODO manage other parameter */
            } else {
              jj_consume_token(-1);
              throw new ParseException();
            }
          } else {
            jj_consume_token(-1);
            throw new ParseException();
          }
        } else {
          jj_consume_token(-1);
          throw new ParseException();
        }
      } else if (jj_2_31(2)) {
        jj_consume_token(0);
                         onHouse();
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    } else if (jj_2_33(2)) {
      jj_consume_token(0);
                 {if (true) return;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void parameter() throws ParseException, UnsupportedRestOperation, RestOperationException, MissingParameterRestOperation {
    jj_consume_token(18);
    label_1:
    while (true) {
      if (jj_2_34(2)) {
        ;
      } else {
        break label_1;
      }
      jj_consume_token(VALUE);
      jj_consume_token(19);
      jj_consume_token(VALUE);
      if (jj_2_35(2)) {
        jj_consume_token(20);

      } else if (jj_2_36(2)) {
        jj_consume_token(0);

      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  private boolean jj_2_15(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(14, xla); }
  }

  private boolean jj_2_16(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_16(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(15, xla); }
  }

  private boolean jj_2_17(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_17(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(16, xla); }
  }

  private boolean jj_2_18(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_18(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(17, xla); }
  }

  private boolean jj_2_19(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_19(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(18, xla); }
  }

  private boolean jj_2_20(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_20(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(19, xla); }
  }

  private boolean jj_2_21(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_21(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(20, xla); }
  }

  private boolean jj_2_22(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_22(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(21, xla); }
  }

  private boolean jj_2_23(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_23(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(22, xla); }
  }

  private boolean jj_2_24(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_24(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(23, xla); }
  }

  private boolean jj_2_25(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_25(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(24, xla); }
  }

  private boolean jj_2_26(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_26(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(25, xla); }
  }

  private boolean jj_2_27(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_27(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(26, xla); }
  }

  private boolean jj_2_28(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_28(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(27, xla); }
  }

  private boolean jj_2_29(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_29(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(28, xla); }
  }

  private boolean jj_2_30(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_30(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(29, xla); }
  }

  private boolean jj_2_31(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_31(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(30, xla); }
  }

  private boolean jj_2_32(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_32(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(31, xla); }
  }

  private boolean jj_2_33(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_33(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(32, xla); }
  }

  private boolean jj_2_34(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_34(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(33, xla); }
  }

  private boolean jj_2_35(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_35(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(34, xla); }
  }

  private boolean jj_2_36(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_36(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(35, xla); }
  }

  private boolean jj_3_31() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3R_2() {
    if (jj_scan_token(18)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_34()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_6() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(CONTROLLER)) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_33() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_19() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_18() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(GROUP)) return true;
    return false;
  }

  private boolean jj_3_29() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(CONTROLLERS)) return true;
    return false;
  }

  private boolean jj_3_10() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_9() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_8() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(LABEL)) return true;
    return false;
  }

  private boolean jj_3_28() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(GROUPS)) return true;
    return false;
  }

  private boolean jj_3_27() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(LABELS)) return true;
    return false;
  }

  private boolean jj_3_14() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_30() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_27()) {
    jj_scanpos = xsp;
    if (jj_3_28()) {
    jj_scanpos = xsp;
    if (jj_3_29()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_24() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_32() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(HOUSE)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_13() {
    if (jj_scan_token(17)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_36() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_23() {
    if (jj_scan_token(17)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_12() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_35() {
    if (jj_scan_token(20)) return true;
    return false;
  }

  private boolean jj_3_11() {
    if (jj_3R_2()) return true;
    return false;
  }

  private boolean jj_3_22() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_21() {
    if (jj_3R_2()) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(17)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_17() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_34() {
    if (jj_scan_token(VALUE)) return true;
    if (jj_scan_token(19)) return true;
    return false;
  }

  private boolean jj_3_16() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(CONTROLLER)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_2()) return true;
    return false;
  }

  private boolean jj_3_15() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_26() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(CONTROLLER)) return true;
    return false;
  }

  private boolean jj_3_25() {
    if (jj_scan_token(16)) return true;
    if (jj_scan_token(VALUE)) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_20() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public UriParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[0];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[36];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public UriParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public UriParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new UriParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public UriParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new UriParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public UriParser(UriParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(UriParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[21];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 0; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 21; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 36; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
            case 13: jj_3_14(); break;
            case 14: jj_3_15(); break;
            case 15: jj_3_16(); break;
            case 16: jj_3_17(); break;
            case 17: jj_3_18(); break;
            case 18: jj_3_19(); break;
            case 19: jj_3_20(); break;
            case 20: jj_3_21(); break;
            case 21: jj_3_22(); break;
            case 22: jj_3_23(); break;
            case 23: jj_3_24(); break;
            case 24: jj_3_25(); break;
            case 25: jj_3_26(); break;
            case 26: jj_3_27(); break;
            case 27: jj_3_28(); break;
            case 28: jj_3_29(); break;
            case 29: jj_3_30(); break;
            case 30: jj_3_31(); break;
            case 31: jj_3_32(); break;
            case 32: jj_3_33(); break;
            case 33: jj_3_34(); break;
            case 34: jj_3_35(); break;
            case 35: jj_3_36(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
