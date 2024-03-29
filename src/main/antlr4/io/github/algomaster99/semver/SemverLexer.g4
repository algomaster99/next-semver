// https://github.com/antlr/grammars-v4/tree/master/semver
lexer grammar SemverLexer;

options { caseInsensitive = true; }

fragment POSITIVE_DIGIT: [1-9];
fragment LETTER: [a-z];

DASH: '-';
PLUS: '+';
DOT: '.';

//most common pre-release "modifiers"
ALPHA: 'alpha';
BETA: 'beta';
RC: 'rc' | 'release' ('-' | '.') 'candidate';
SNAPSHOT: 'snapshot';
PREVIEW: 'p' | 'pre' | 'preview';
DEV: 'dev' | 'devel' | 'development';
MILESTONE: 'mt' | 'milestone';
DAILY: 'daily';
NIGHTLY: 'nightly';
BUILD: 'bld' | 'build';
TEST: 'test';
EXPERIMENTAL: 'experimental';

NUMBER: [0-9]+;

IDENTIFIER: LETTER+;
