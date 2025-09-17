<script lang="ts">
    import WeeklyCalendar from "$lib/calendars/+MonthlyCalendar.svelte";
    import { add, format, setDate, sub } from "date-fns";
    
    let date = $state(setDate(Date(), 1))
</script>
<main>
    <h1>Schedule</h1>
    <h2>{format(date, "MMMM y")}</h2>
    <button onclick={() => {date = sub(date, {months: 1})}}>-</button>
    <button onclick={() => {date = add(date, {months: 1})}}>+</button>
    <WeeklyCalendar contains={date} />
</main>