# appoint
Appoint is a flexible appointment scheduling software.

## Features
- Schedules can be constructed from weekly availability slots and overlaid using calendars from ICS files (either uploaded or by URL), CalDAV, or custom overrides set in Appoint.
- Overlays can be either adding or removing busy times, for ICS / CalDAV inputs you can also filter events by specific parameters (i.e. if the name contains a certain string, do not schedule then)
- Multiple people can be on the same appointment schedule, and appointments are assigned based on who is free at a specific time. In the case that multiple people are assigned at the same time, Appoint will assign the person with the least scheduled appointments in a week, or go round-robin.
- Appointments can have multiple slots, so people can sign up for the same time (this can be configured per calendar).
- Event reminders / ICS files for importing can be sent via email, or can be directly added to calendars (integration for Google Calendar / Outlook coming At Some Point&trade;)

## Installation
Coming soon

## Architecture
Appoint consists of two components:
- The [backend](backend/README.md), a Scalatra service that handles all of the backend logic.
- The [frontend](frontend/README.md), a SvelteKit webapp which end users will interact with.

The backend will connect with a database (likely Postgres, to be implemented) for data persistence. The full stack can be launched using the [Docker Compose](compose.yml) file at the root of this repository. Nix derivations will also be added at a later date.

## License
This program is licensed under the [GNU General Public License, version 3](LICENSE.md).

*This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
version.*<br />
*This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.*
<br />
*You should have received a copy of the GNU General Public License along with this program. If not, see
https://www.gnu.org/licenses/.*
