# Convert Rank to a Java enum

## Problem
`src\swing\Rank.java` implements the pre-Java-5 "typesafe enum" pattern (private
constructor, public static final instances, a manual `VALUES`/`RANKS` list).
Since the project targets a modern JDK, this should be a real Java `enum`,
which gives compiler-enforced exhaustiveness, `switch` support, built-in
`values()`/`valueOf()`/`ordinal()`, and removes the hand-rolled instance list.

No other files in the repo currently reference `Rank`, so this is a low-risk,
self-contained conversion.

## Approach
- Convert `Rank` from `public final class` to `public enum Rank`.
- Replace the 13 `new Rank(...)` static field declarations with enum
  constants: `TWO(2, "2"), THREE(3, "3"), ..., ACE(11, "A");`
- Keep the existing public API so any future/consuming code is unaffected:
  - `getRank()` — returns the int rank value.
  - `toString()` — returns the display string (override, same as today).
  - `RANKS` — keep as `public static final List<Rank> RANKS`, but derive it
    from `Arrays.asList(values())` wrapped in `Collections.unmodifiableList`,
    instead of the old manual `VALUES` array.
- Drop the private constructor's role as "the only way to create instances"
  comment nuance — enum constructors are implicitly private already, so make
  the constructor just `Rank(int rank, String display)`.
- Remove now-unnecessary `Arrays`/`Collections` usage only if replaced by
  simpler `values()`-based logic (still need `Collections.unmodifiableList`
  and `Arrays.asList` for the `RANKS` field, so imports stay).

## Notes
- No other source files reference `Rank` yet, so no call-site updates needed.
- Behavior preserved: `Rank.ACE.getRank() == 11`, `Rank.TEN.toString() == "10"`,
  `Rank.RANKS` iterates in TWO..ACE order, all same as before.
- No new tests exist for this class; will do a quick `javac` compile check
  after the change to confirm it compiles cleanly.
