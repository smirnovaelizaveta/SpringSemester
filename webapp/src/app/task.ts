export interface Task {
  id: string;
  name: string;
  description: string;
  status: Status
}

export enum Status {
  Solved,
  InProgress,
  NotSolved
}