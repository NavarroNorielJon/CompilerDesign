// Original Grammar:
// 	<program> -> <access_mod> <class> <iden> <stmt_list>
// 	<access_mod> -> <public> | <private> | EPSILON
// 	<declaration> -> <access_mod> <datatype> <iden> | <access_mod> <datatype> <iden> <assignment>
// 	<assignment> -> <assign> <expr>
// 	<if_stmnt> -> <if> <condition> <stmnt_list> <if_stmnt> | <end>
// 	<while_stmnt> -> <until> <condition> <stmt_list> <end>
// 	<input> -> <datatype> <iden> <assign> <scanner>
// 	<output> -> <print> <expr>
// 	<stmt_list> -> <stmt> <stmt_list'>
// 	<stmt_list'> -> <stmt> <stmt_list'>
// 	<stmt_list'> -> EPSILON
// 	<stmt> -> <assignment> | <declaration> | <input> | <output> | <if_stmnt> | <while_stmnt>
// 	<condition> -> <term> <log_operator> <condition> | <term> <rel_operator> <condition> | <term>
// 	<math_expr> -> <term> <operator> <math_expr> | <term>
// 	<operator> -> <add> | <multiply> | <subtract> | <int_divide> | <modulo>
// 	<log_operator> -> <less_than> | <greater_than> | <not_equal> | <is_equal> | <lte> | <gte> | <not>
// 	<rel_operator> -> <and> | <or>
// 	<bool_val> -> <true> | <false>
// 	<datatype> -> <integer> | <decimal> | <long> | <boolean> | <character> | <string>
// 	<expr> -> <quot_string> <term> <expr> <quot_string>| <term> <expr> | <quot_char> <term> <quot_char> | <number> | <bool_val> | <math_expr> | <rel_expr> | <term>
// 	<term> -> <iden> | <number>

// Predict Set:
// 	1	<program> → <access_mod> <class> <iden> <stmt_list>	<public>, <private>, <class>
// 	2	<access_mod> → <public>	<public>
// 	3	<access_mod> → <private>	<private>
// 	4	<access_mod> → ε	<integer>, <decimal>, <long>, <boolean>, <character>, <string>, <class>
// 	5	<declaration> → <access_mod> <datatype> <iden>	<public>, <private>, <integer>, <decimal>, <long>, <boolean>, <character>, <string>
// 	6	<declaration> → <access_mod> <datatype> <iden> <assignment>	<public>, <private>, <integer>, <decimal>, <long>, <boolean>, <character>, <string>
// 	7	<assignment> → <assign> <expr>	<assign>
// 	8	<if_stmnt> → <if> <condition> <stmnt_list> <if_stmnt>	<if>
// 	9	<if_stmnt> → <end>	<end>
// 	10	<while_stmnt> → <until> <condition> <stmt_list> <end>	<until>
// 	11	<input> → <datatype> <iden> <assign> <scanner>	<integer>, <decimal>, <long>, <boolean>, <character>, <string>
// 	12	<output> → <print> <expr>	<print>
// 	13	<stmt_list> → <stmt> <stmt_list'>	<assign>, <public>, <private>, <integer>, <decimal>, <long>, <boolean>, <character>, <string>, <print>, <until>, <if>, <end>
// 	14	<stmt_list'> → <stmt> <stmt_list'>	<assign>, <public>, <private>, <integer>, <decimal>, <long>, <boolean>, <character>, <string>, <print>, <until>, <if>, <end>
// 	15	<stmt_list'> → ε	<end>, $
// 	16	<stmt> → <assignment>	<assign>
// 	17	<stmt> → <declaration>	<public>, <private>, <integer>, <decimal>, <long>, <boolean>, <character>, <string>
// 	18	<stmt> → <input>	<integer>, <decimal>, <long>, <boolean>, <character>, <string>
// 	19	<stmt> → <output>	<print>
// 	20	<stmt> → <if_stmnt>	<if>, <end>
// 	21	<stmt> → <while_stmnt>	<until>
// 	22	<condition> → <term> <log_operator> <condition>	<iden>, <number>
// 	23	<condition> → <term> <rel_operator> <condition>	<iden>, <number>
// 	24	<condition> → <term>	<iden>, <number>
// 	25	<math_expr> → <term> <operator> <math_expr>	<iden>, <number>
// 	26	<math_expr> → <term>	<iden>, <number>
// 	27	<operator> → <add>	<add>
// 	28	<operator> → <multiply>	<multiply>
// 	29	<operator> → <subtract>	<subtract>
// 	30	<operator> → <int_divide>	<int_divide>
// 	31	<operator> → <modulo>	<modulo>

package edu.slu.cs311b;
import java.util.Deque;
import java.util.LinkedList;

	// @author Bantayan, Joshua John
	// @author Collado, Patrick Noel
	// @author Navarro, Noriel Jon