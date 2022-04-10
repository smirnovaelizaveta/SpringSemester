export interface Project {
  files: ProjectFile[];
}

export interface ProjectFile {
  name: string;
  content?: string;
  children?: ProjectFile[];
  readonly: boolean;
}